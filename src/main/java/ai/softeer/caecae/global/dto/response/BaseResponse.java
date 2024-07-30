package ai.softeer.caecae.global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BaseResponse {
    // 커스텀 응답 코드 종류
    private int responseCode;
    // 응답 정보를 담은 메시지
    private String message;
}
