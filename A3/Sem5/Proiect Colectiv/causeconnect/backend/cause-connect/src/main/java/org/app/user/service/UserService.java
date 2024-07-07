package org.app.user.service;

import java.util.List;

import org.app.user.domain.User;
import org.app.user.dtos.UserDTO;
import org.app.user.repos.UserRepository;
import org.app.errors.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//For login, in case they need to be deleted
import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Value("${jwt.secretKey}")
    private String secretKey;

    private static final long expirationTime = 864_000_000;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));

        return users.stream()
                .map(user -> mapToDTO(user))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);

        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public boolean usernameExists(final String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public User userExists(final String username,final String email, final String password)
    {
        final Optional<User> user = userRepository.findAll().stream()
                .filter(u ->
                        (Objects.equals(u.getUsername(), username) ||
                                Objects.equals(u.getEmail(), email)) &&
                                Objects.equals(u.getPassword(), password))
                .findFirst();
        return user.orElse(null);
    }

    public User getUserFromUsername(final String username)
    {
        final Optional<User> user = userRepository.findAll().stream()
                .filter(u -> Objects.equals(u.getUsername(), username)).findFirst();
        return user.orElse(null);
    }

    public String createToken(String identifier) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder().setSubject(identifier)
                .setIssuedAt(now).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public User tokenIsValid(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return getUserFromUsername(claims.getSubject());
        }
        catch (Exception e)
        {
            return null;
        }
    }

}