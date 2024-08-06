package ai.softeer.caecae.racinggame.domain.exception;

import ai.softeer.caecae.global.enums.ErrorCode;
import lombok.Getter;

@Getter
public class RacingGameException extends RuntimeException {
    private final ErrorCode errorCode;

    public RacingGameException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
