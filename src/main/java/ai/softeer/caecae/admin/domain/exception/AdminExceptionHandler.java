package ai.softeer.caecae.admin.domain.exception;

import ai.softeer.caecae.global.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * RacingGame 도메인에서 에러를 핸들링하여 HttpResponse 를 반환하는 핸들러
 */
@Slf4j
@ControllerAdvice
public class AdminExceptionHandler {
    // RacingGameException 에 대한 에러 핸들링
    @ExceptionHandler(value = AdminException.class)
    public ResponseEntity<ErrorResponse> handleRacingGameException(AdminException adminException) {
        log.error(adminException.getMessage(), adminException);
        return ErrorResponse.of(adminException.getErrorCode());
    }
}
