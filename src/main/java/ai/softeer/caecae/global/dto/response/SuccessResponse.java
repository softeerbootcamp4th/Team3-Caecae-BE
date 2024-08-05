package ai.softeer.caecae.global.dto.response;

import ai.softeer.caecae.global.enums.SuccessCode;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * Http 요청에 대한 성공 응답 본문에 반환할 객체
 */
@Getter
public class SuccessResponse<T> extends BaseResponse {
    // httpResponse 를 통해 넘겨 줄 응답 데이터
    @Nullable
    private T data;
    // 응답 성공 관련 정보

    // 성공 관련 정보,  반환 데이터를 파라미터로 받는 생성자
    private SuccessResponse(SuccessCode successCode, T data) {
        super(successCode.getResponseCode(), successCode.getMessage());
        this.data = data;
    }

    // 성공 관련 정보,  반환 데이터를 파라미터로 받는 생성자
    private SuccessResponse(SuccessCode successCode) {
        super(successCode.getResponseCode(), successCode.getMessage());
    }


    // 팩토리 메서드 부분
    // Controller 에서 사용할 ResponseEntity 를 반환하는 팩토리메서드
    public static <T> ResponseEntity<SuccessResponse<T>> of(SuccessCode successCode, T data) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(new SuccessResponse<>(successCode, data));
    }

    public static ResponseEntity<SuccessResponse<Object>> of(SuccessCode successCode) {
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(new SuccessResponse<>(successCode));
    }


}
