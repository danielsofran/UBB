package org.app.cause.service;

import org.app.cause.domain.Cause;
import org.app.cause.dtos.CauseDTO;
import org.app.cause.repos.CauseRepository;
import org.app.comments.repos.CommentRepository;
import org.app.photo.repos.PhotoRepository;
import org.app.errors.exceptions.NotFoundException;
import org.app.reaction.domain.ReactionType;
import org.app.reaction.repos.CauseReactionRepository;
import org.app.user.domain.User;
import org.app.user.repos.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CauseService {

    private final CauseRepository causeRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final CauseReactionRepository causeReactionRepository;
    private final CommentRepository commentRepository;

    public CauseService(final CauseRepository causeRepository,
                        final UserRepository userRepository,
                        final PhotoRepository photoRepository,
                        CauseReactionRepository causeReactionRepository,
                        CommentRepository commentRepository) {
        this.causeRepository = causeRepository;
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.causeReactionRepository = causeReactionRepository;
        this.commentRepository = commentRepository;
    }

    public List<CauseDTO> findAll() {
        final List<Cause> causes = causeRepository.findAll(Sort.by("id"));
        return causes.stream()
                .sorted(Comparator.comparing(this::calculatePointsForCause).reversed())
                .map(cause -> mapToDTO(cause, new CauseDTO()))
                .toList();
    }

    public CauseDTO get(final Long id) {
        return causeRepository.findById(id)
                .map(cause -> mapToDTO(cause, new CauseDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public List<CauseDTO> getAllBelongingTo(final Long id) {
        return causeRepository.findAll().stream().filter(
                        cause -> Objects.equals(cause.getUser().getId(), id))
                .map(cause -> mapToDTO(cause, new CauseDTO()))
                .toList();
    }

    public Long create(final CauseDTO causeDTO) {
        final Cause cause = new Cause();
        mapToEntity(causeDTO, cause);
        return causeRepository.save(cause).getId();
    }

    public void update(final Long id, final CauseDTO causeDTO) {
        final Cause cause = causeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(causeDTO, cause);
        causeRepository.save(cause);
    }

    @Transactional
    public void delete(final Long id) {
        // must delete all photos for this cause
        photoRepository.findPhotosForCauseById(id).forEach(photo -> {
            photoRepository.deleteById(photo.getId());
        });
        causeRepository.deleteById(id);
    }

    private CauseDTO mapToDTO(final Cause cause, final CauseDTO causeDTO) {
        causeDTO.setId(cause.getId());
        causeDTO.setName(cause.getName());
        causeDTO.setDescription(cause.getDescription());
        causeDTO.setCauseType(cause.getCauseType());
        causeDTO.setDate(cause.getDate());
        causeDTO.setDeadline(cause.getDeadline());
        causeDTO.setLocation(cause.getLocation());
        causeDTO.setMoneyTarget(cause.getMoneyTarget());
        causeDTO.setMoneyObtained(cause.getMoneyObtained());
        causeDTO.setScore(cause.getScore());
        if (cause.getUser() == null) {
            throw new NotFoundException("User not found!");
        }
        causeDTO.setUserId(cause.getUser().getId());
        return causeDTO;
    }

    private Cause mapToEntity(final CauseDTO causeDTO, final Cause cause) {
        cause.setName(causeDTO.getName());
        cause.setDescription(causeDTO.getDescription());
        cause.setCauseType(causeDTO.getCauseType());
        cause.setDate(causeDTO.getDate());
        cause.setDeadline(causeDTO.getDeadline());
        cause.setLocation(causeDTO.getLocation());
        cause.setMoneyTarget(causeDTO.getMoneyTarget());
        cause.setMoneyObtained(causeDTO.getMoneyObtained());
        cause.setScore(causeDTO.getScore());
        final User user = causeDTO.getUserId() == null ? null : userRepository.findById(causeDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("user not found"));
        cause.setUser(user);
        return cause;
    }

    private double calculatePointsForCause(final Cause cause) {
        long commentsCount = commentRepository.findAllCommentsForCause(cause.getId()).size();
        long loveReactions = 0L, likeReactions = 0, sadReactions = 0, negativeReactions = 0;

        for (ReactionType reactionType : ReactionType.values()) {
            Long reactionsCount = causeReactionRepository.countReactionsByCauseIdAndType(cause.getId(), reactionType);

            if (reactionType.equals(ReactionType.ANGRY)) {
                negativeReactions += reactionsCount;
            } else if (reactionType.equals(ReactionType.LIKE)) {
                likeReactions += reactionsCount;
            } else if (reactionType.equals(ReactionType.LOVE)) {
                loveReactions += reactionsCount;
            } else if (reactionType.equals(ReactionType.SAD)) {
                sadReactions += reactionsCount;
            }
        }

        return 0.5 * sadReactions + 0.75 * likeReactions + loveReactions - 0.5 * negativeReactions + commentsCount;
    }
  
    public CauseDTO donateForCause(Long causeId, Long donatedSum) {
        CauseDTO causeDTO = new CauseDTO();
        Cause cause = causeRepository.findById(causeId)
                .orElseThrow(NotFoundException::new);
        Long moneyObtained = cause.getMoneyObtained();
        cause.setMoneyObtained(moneyObtained + donatedSum);
        causeRepository.save(cause);
        return mapToDTO(cause, causeDTO);
    }
}
