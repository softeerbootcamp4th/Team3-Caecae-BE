package ai.softeer.caecae.global.dto.response;

import ai.softeer.caecae.global.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * Http 요청에 대한 실패 응답 본문에 반환할 객체
 */
@Getter
public class ErrorResponse extends BaseResponse {

    private ErrorResponse(ErrorCode errorCode) {
        super(errorCode.getResponseCode(),errorCode.getMessage());
    }

    public static ResponseEntity<ErrorResponse> of(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode));
    }

}
