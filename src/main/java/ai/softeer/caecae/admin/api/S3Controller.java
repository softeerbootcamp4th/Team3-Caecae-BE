package ai.softeer.caecae.admin.api;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.utils.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    //TODO : admin 도메인의 정답 정보 등록 컨트롤러로 종속시키기
    @PostMapping("/api/s3")
    public ResponseEntity<SuccessResponse<String>> upload(@RequestParam("file") MultipartFile file) {
        String filePath = s3Service.uploadFile(file);
        return ResponseEntity.ok(new SuccessResponse<>(filePath));
    }

}
