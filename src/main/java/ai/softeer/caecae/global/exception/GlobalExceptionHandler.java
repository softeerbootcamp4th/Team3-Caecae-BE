package ai.softeer.caecae.global.exception;

import ai.softeer.caecae.global.dto.response.ErrorResponse;
import ai.softeer.caecae.global.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 전역 에러를 핸들링하여 HttpResponse 를 반환하는 핸들러
 */
@Slf4j
public class GlobalExceptionHandler {
    // GlobalException 에 대한 에러 핸들링
    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(GlobalException globalException) {
        log.error(globalException.getMessage(), globalException);
        return ErrorResponse.of(globalException.getErrorCode());
    }

    // 그 외 발생한 에러 핸들링
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
