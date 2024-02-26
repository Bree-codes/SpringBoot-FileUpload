package springboot.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springboot.fileupload.repository.UploadRepo;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class UploadService {

    @Autowired
    private UploadRepo uploadRepo;

    public String storeFiles(MultipartFile file) throws IOException {
         Files files = Files
                 .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(file.getBytes())
                .build();
        files=  uploadRepo.save(files);

        if (files.getId() != null) {
            return "File uploaded successfuly into database";
        }

        return null;
    }
    public byte[] getFiles(String fileName) {
        return uploadRepo.findByName(fileName).getImageData();
    }

    
}
