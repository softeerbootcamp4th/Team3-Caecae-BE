package ai.softeer.caecae.global.dto.response;

public class SuccessResponse<T> extends BaseResponse {
    // httpResponse 를 통해 넘겨 줄 응답 데이터
    private T data;
}
