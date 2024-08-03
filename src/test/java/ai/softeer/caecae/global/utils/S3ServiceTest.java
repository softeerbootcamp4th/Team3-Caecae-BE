package ai.softeer.caecae.global.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class S3ServiceTest {

    @Mock
    private AmazonS3 amazonS3;

    @InjectMocks
    private S3Service s3Service;

    private MockMultipartFile mockMultipartFile;

    @BeforeEach
    void setUp() throws IOException {
        //given
        File file = ResourceUtils.getFile("classpath:hyundai.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        mockMultipartFile = new MockMultipartFile(
                "file", // 파라미터 이름
                file.getName(), // 원본 파일 이름
                "image/png", // 파일 타입
                fileInputStream // 파일 데이터
        );
    }

    //TODO : 통합테스트를 위한 CICD 과정에서 의존성 주입
    @Disabled
    @Test
    @DisplayName("멀티파트 파일을 S3에 업로드하고 url을 반환함")
    void uploadFile() throws MalformedURLException {
        //given
        String expectedUrl = "https://test-bucket.s3.amazonaws.com/caecae-uuid.png";

        when(amazonS3.getUrl(anyString(), anyString())).thenReturn(new java.net.URL(expectedUrl));
        doNothing().when(amazonS3).putObject(any(PutObjectRequest.class));

        //when
        String result = s3Service.uploadFile(mockMultipartFile);

        //then
        assertEquals(expectedUrl, result);
        verify(amazonS3, times(1)).putObject(any(PutObjectRequest.class));
    }
}
