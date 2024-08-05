package ai.softeer.caecae.racinggame.domain.exception;

import ai.softeer.caecae.global.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * RacingGame 도메인에서 에러를 핸들링하여 HttpResponse 를 반환하는 핸들러
 */
@ControllerAdvice
@Slf4j
public class RacingGameExceptionHandler {
    // RacingGameException 에 대한 에러 핸들링
    @ExceptionHandler(value = RacingGameException.class)
    public ResponseEntity<ErrorResponse> handleRacingGameException(RacingGameException racingGameException) {
        log.error(racingGameException.getMessage(), racingGameException);
        return ErrorResponse.of(racingGameException.getErrorCode());
    }
}
