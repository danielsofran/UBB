package org.app.photo.service;

import org.app.cause.domain.Cause;
import org.app.cause.repos.CauseRepository;
import org.app.errors.exceptions.NotFoundException;
import org.app.photo.domain.Photo;
import org.app.photo.dtos.PhotoDTO;
import org.app.photo.repos.PhotoRepository;
import org.app.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    private static final Logger LOG = LoggerFactory.getLogger(PhotoService.class);

    private final PhotoRepository photoRepository;
    private final CauseRepository causeRepository;
    private final String baseImagePath;

    public PhotoService(PhotoRepository photoRepository,
                        CauseRepository causeRepository,
                        @Value("${image.basePath}") String baseImagePath) {
        this.photoRepository = photoRepository;
        this.causeRepository = causeRepository;
        this.baseImagePath = baseImagePath;
    }

    public PhotoDTO get(final Long id) {
        LOG.info("Retrieving photo by id: " + id);
        return photoRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(NotFoundException::new);
    }

    public List<PhotoDTO> getPhotosForCauseById(final Long causeID) {
        LOG.info("Retrieving all photos by cause id: " + causeID);
        return photoRepository.findPhotosForCauseById(causeID).stream()
                .map(this::mapToDto)
                .toList();
    }

    @Transactional
    public void saveAllPhotosForCauseID(Long causeId, MultipartFile[] files) {
        LOG.info("Saving all photos for a cause : " + causeId);

        List<PhotoDTO> photoDTOS = getPhotosForCauseById(causeId);

        for (MultipartFile file : files) {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setCauseId(causeId);
            if (!file.isEmpty()) {
                Optional<File> serverFile = FileUtils.upload(file, baseImagePath + "causes/");
                serverFile.ifPresent(value -> photoDTO.setPath(value.getPath()));
                Long photoID = create(photoDTO);
                LOG.info("Created photo with id: " + photoID);
            }
        }

        for(PhotoDTO photoDTO : photoDTOS){
            removePhoto(photoDTO);
            // do not delete the file, just remove the photo from the database
            // FileUtils.delete(new File(photoDTO.getPath()));
        }
        LOG.info("All old photos removed for cause id: " + causeId);

    }

    public Long create(final PhotoDTO photoDTO) {
        final Photo photo = new Photo();
        mapToEntity(photoDTO, photo);
        LOG.info("PhotoDTO mapped to an entity");

        LOG.info("Saving photo...");
        return photoRepository.save(photo).getId();
    }

    public void removeAllPhotosForCauseID(Long causeId) {
        LOG.info("Retrieving all photos for cause : " + causeId);
        List<PhotoDTO> photoDTOS = getPhotosForCauseById(causeId);
        LOG.info("Retrieved a total of " + photoDTOS.size() + " no of photos");
        for (PhotoDTO photoDTO : photoDTOS) {
            removePhoto(photoDTO);
            // FileUtils.delete(new File(photoDTO.getPath()));
        }
        LOG.info("All photos has been removed for cause : " + causeId);
    }

    public void removePhoto(final PhotoDTO photoDTO) {
        LOG.info("Removing photo...");
        Long photoID = photoDTO.getId();
        if (photoID == null) {
            LOG.info("Photo id: " + photoID);
            throw new NotFoundException("Photo does not have an id!");
        }

        photoRepository.findById(photoID)
                .orElseThrow(NotFoundException::new);
        LOG.info("Photo exists");


        photoRepository.deleteById(photoID);
        LOG.info("Photo successfully deleted!");
    }

    private PhotoDTO mapToDto(Photo photo) {
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setId(photo.getId());
        photoDTO.setPath(photo.getPath());

        if (photo.getCause() == null) {
            throw new NotFoundException("Cause not found!");
        }
        photoDTO.setCauseId(photo.getCause().getId());
        return photoDTO;
    }

    private Photo mapToEntity(final PhotoDTO photoDTO, Photo photo) {
        photo.setId(photoDTO.getId());
        photo.setPath(photoDTO.getPath());

        final Long causeID = photoDTO.getCauseId();
        if (causeID == null) {
            throw new NotFoundException("Cause ID not found");
        }

        final Cause cause = causeRepository.findById(causeID).
                orElseThrow(() -> new NotFoundException("Cause not found"));
        photo.setCause(cause);
        return photo;
    }
}
