package ai.softeer.caecae.global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Http 요청에 대한 응답(성공,실패)시 본문에 반환할 객체
 */
@Getter
@AllArgsConstructor
public abstract class BaseResponse {
    // 커스텀 응답 코드 종류
    // TODO : 커스텀 응답코드 문서화
    private int responseCode;
    
    // 응답 정보를 담은 메시지
    private String message;
}
