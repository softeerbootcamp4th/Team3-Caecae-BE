package ai.softeer.caecae.global.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessResponse<T> extends BaseResponse {
    // httpResponse 를 통해 넘겨 줄 응답 데이터
    private T data;

    // 응답코드, 메세지, 반환 데이터를 파라미터로 받는 생성자
    public SuccessResponse(int responseCode, String message, T data) {
        super(responseCode, message);
        this.data = data;
    }

    // 코드 및 메시지를 설정하지 않은 생성자
    public SuccessResponse(T data) {
        super(0, "요청 성공 기본 메시지 입니다.");
        this.data = data;
    }
}
