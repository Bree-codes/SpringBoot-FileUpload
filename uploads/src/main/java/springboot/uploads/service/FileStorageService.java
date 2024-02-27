package springboot.uploads.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import springboot.uploads.exceptions.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path fileStorageLocation = Paths.get("/path/to/your/upload/directory");

    public String storeFile(MultipartFile file, String userId) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new StorageException("Filename contains invalid path sequence " + fileName);
            }

            // Create directory for user if not exists
            Path userDirectory = fileStorageLocation.resolve(userId);
            Files.createDirectories(userDirectory);

            // Copy file to the target location (overwrite existing file with the same name)
            Path targetLocation = userDirectory.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // Return the file path
            return targetLocation.toString();
        } catch (IOException ex) {
            throw new StorageException("Failed to store file " + fileName, ex);
        }
    }
}
