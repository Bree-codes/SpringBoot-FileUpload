package springboot.uploads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import springboot.uploads.service.FileStorageService;

@RequestMapping("/api")
@Controller
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        try {
            String filePath = fileStorageService.storeFile(file, userId);
            return ResponseEntity.ok().body("File uploaded successfully. Path: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    // Add additional methods or mappings as needed
}
