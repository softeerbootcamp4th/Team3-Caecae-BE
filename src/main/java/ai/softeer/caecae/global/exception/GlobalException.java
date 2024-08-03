package ai.softeer.caecae.global.exception;

import ai.softeer.caecae.global.enums.ErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;


    }

}
