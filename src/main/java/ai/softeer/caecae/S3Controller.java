package ai.softeer.caecae;

import ai.softeer.caecae.global.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;


    @PostMapping("/api/s3")
    public String upload(@RequestParam("file") MultipartFile file) {
        String filePath = s3Service.uploadFile(file);
        return filePath + "created!";
        //TODO : ResponseEntity 생성하기

    }

    @PostMapping("/api/health")
    public String healthCheck() {
        return "Hello Casper!";
    }
}
