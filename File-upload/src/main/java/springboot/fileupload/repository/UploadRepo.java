package springboot.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.fileupload.entity.FileEntity;

public interface UploadRepo extends JpaRepository<FileEntity,Long> {
    FileEntity findByName(String name);
}
