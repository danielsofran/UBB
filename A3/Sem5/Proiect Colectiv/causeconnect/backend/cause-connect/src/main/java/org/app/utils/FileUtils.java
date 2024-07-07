package org.app.utils;

import org.app.errors.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    public static Optional<File> upload(MultipartFile sourceFile, String path) {

        LOG.info("Uploading a file: " + sourceFile.getOriginalFilename());

        if (!Objects.equals(sourceFile.getOriginalFilename(), "") &&
                !Objects.requireNonNull(sourceFile.getOriginalFilename()).matches(".+\\.(png|jpeg|jpg)$")) {
            LOG.error("File is not jpg/jpeg/png");
            throw new NotFoundException("File is not jpg/jpeg/png");
        }

        File targetFile = new File(path + new Date().getTime() + "_" + sourceFile.getOriginalFilename());
        try (InputStream inputStream = sourceFile.getInputStream()) {
            Files.createDirectories(Paths.get(path));
            Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LOG.info("File created locally! -> " + targetFile.getPath());
        } catch (IOException ex) {
            LOG.info("Returning an empty file, an exception occurred during the image manipulation!");
            return Optional.empty();
        }

        LOG.info("Returning the new file...");
        return Optional.of(targetFile);
    }

    public static void delete(File file) {
        if (file.exists() && !file.isDirectory()) {
            LOG.info("File exists...");
            file.delete();
            LOG.info("File deleted!");
        }
    }

}
