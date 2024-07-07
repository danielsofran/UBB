package org.app.user.rest;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;

import org.app.photo.service.PhotoService;
import org.app.user.domain.User;
import org.app.user.dtos.UserDTO;
import org.app.user.login.LoginDTO;
import org.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*")
public class UserRestController {

    private final UserService userService;
    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);


    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
        final Long createdId = userService.create(userDTO);

        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUser(@PathVariable final Long id, @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody final UserDTO userDTO) {
        LOG.debug("Entered login");
        User user = userService.userExists(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        if(user == null)
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        var token = userService.createToken(user.getUsername());
        return ResponseEntity.ok(new LoginDTO(user.getId(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody final UserDTO userDTO) {
        LOG.debug("Entered register");
        User user = userService.userExists(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        if(user != null)
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        final Long createdId = userService.create(userDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

}
