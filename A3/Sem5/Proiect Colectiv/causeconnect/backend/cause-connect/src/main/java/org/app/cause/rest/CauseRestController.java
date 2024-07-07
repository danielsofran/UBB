package org.app.cause.rest;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import org.apache.coyote.Response;
import org.app.cause.dtos.CauseDTO;
import org.app.cause.service.CauseService;
import org.app.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/causes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CauseRestController {

    private final CauseService causeService;

    public CauseRestController(CauseService causeService) {
        this.causeService = causeService;
    }

    @GetMapping
    public ResponseEntity<List<CauseDTO>> getAllCauses() {
        return ResponseEntity.ok(causeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CauseDTO> getCause(@PathVariable final Long id) {
        return ResponseEntity.ok(causeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCause(@RequestBody @Valid final CauseDTO causeDTO) {
        final Long createdId = causeService.create(causeDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCause(@PathVariable final Long id,
                                            @RequestBody @Valid final CauseDTO causeDTO) {
        causeService.update(id, causeDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCause(@PathVariable final Long id) {
        causeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myCauses")
    public ResponseEntity<?> getOwnCauses(HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        if(userID != null)
            return ResponseEntity.ok(causeService.getAllBelongingTo(userID));
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This user account doesn't exist");
    }

    @PostMapping("/donate/{causeId}")
    public ResponseEntity<?> donateForCause(@PathVariable final Long causeId,
                                            @RequestParam final Optional<Long> donatedSum) {
        if(donatedSum.isEmpty() || donatedSum.get() <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        CauseDTO updatedCause = causeService.donateForCause(causeId, donatedSum.get());
        return ResponseEntity.ok(updatedCause);
    }

}
