package ai.softeer.caecae.global.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException; // 추가
import java.io.InputStream; // 추가
import java.util.UUID; // 추가

@Slf4j
@Service
//TODO : JPA 의존성 추가 후 @Transcational
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    //TODO : 환경변수로 관리
//    @Value("${cloud.aws.s3.bucket}")
    private String bucket = "caecae-bucket";


    /**
     * S3에 파일을 업로드 하는 서비스 로직
     * @param file
     * @return 파일 이름
     */
    //TODO : JPA 의존성 추가 후 @Transcational
    public String uploadFile(MultipartFile file) {
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        String filePath;
        // 파일 업로드
        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
//                    .withCannedAcl(CannedAccessControlList.PublicRead));

            // 올린 오브젝트에 대한 s3 url
             filePath = amazonS3.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("파일이 없습니다.");
            //TODO : 커스텀 에러 관리하기
        }
        log.info(filePath," is successfully created in S3");
        return filePath;
    }

    // caecae+UUID 로 파일 이름 생성하기
    private String createFileName(String fileName) {
        return "caecae-" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // 파일 확장자 추출하기
    private String getFileExtension(String fileName) {
        log.info("fileName : {}", fileName.substring(fileName.lastIndexOf(".")));
        return fileName.substring(fileName.lastIndexOf("."));
    }
}