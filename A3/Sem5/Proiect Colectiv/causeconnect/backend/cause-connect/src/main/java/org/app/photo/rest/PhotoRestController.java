package org.app.photo.rest;

import org.app.cause.dtos.CauseDTO;
import org.app.cause.service.CauseService;
import org.app.photo.dtos.PhotoDTO;
import org.app.photo.service.PhotoService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhotoRestController {

    private static final Logger LOG = LoggerFactory.getLogger(PhotoRestController.class);

    private final PhotoService photoService;
    private final CauseService causeService;

    public PhotoRestController(PhotoService photoService, CauseService causeService) {
        this.photoService = photoService;
        this.causeService = causeService;
    }

    @GetMapping("/photos/{photoID}")
    public ResponseEntity<?> getPhoto(@PathVariable final Long photoID) {
        PhotoDTO photoDTO = photoService.get(photoID);
        try{
            byte[] image = Files.readAllBytes(Paths.get(photoDTO.getPath()));
            ByteArrayResource resource = new ByteArrayResource(image);

            MediaType mediaType;
            var photoPath = photoDTO.getPath();
            if (photoPath.endsWith(".png")) {
                mediaType = MediaType.IMAGE_PNG;
            } else if (photoPath.endsWith("jpg") || photoPath.endsWith("jpeg")) {
                mediaType = MediaType.IMAGE_JPEG;
            } else if (photoPath.endsWith("webp")) {
                mediaType = MediaType.valueOf("image/webp");
            } else { // AVIF
                mediaType = MediaType.valueOf("image/avif");
            }

            return ResponseEntity.ok()
                    .contentLength(image.length)
                    .contentType(mediaType)
                    .body(resource);
        }
        catch (Exception e){
            LOG.info("GET PHOTO EX:\n"+e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/causes/photos/{causeID}")
    public ResponseEntity<List<PhotoDTO>> getAllPhotosForCause(@PathVariable final Long causeID) {
        return ResponseEntity.ok(photoService.getPhotosForCauseById(causeID));
    }

    @PostMapping(value = "/causes/photos/{causeID}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<PhotoDTO>> savePhotosForCause(@PathVariable("causeID") final Long causeID,
                                                             @RequestPart("files") MultipartFile[] files) {
        CauseDTO causeDTO = causeService.get(causeID);
        photoService.saveAllPhotosForCauseID(causeDTO.getId(), files);

        return new ResponseEntity<>(photoService.getPhotosForCauseById(causeID), HttpStatus.CREATED);
    }

}
