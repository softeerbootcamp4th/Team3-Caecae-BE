package ai.softeer.caecae.global.dto.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends BaseResponse{
    public ErrorResponse(int responseCode, String message) {
        super(responseCode, message);
    }

}
