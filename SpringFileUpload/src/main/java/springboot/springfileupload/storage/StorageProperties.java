package springboot.springfileupload.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
@Component
@Data
public class StorageProperties {

    private String location = "upload-dir";


}