package ai.softeer.caecae.global.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Http 요청에 대한 에러 응답과 관련있는 정보
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode implements BaseCode{
    //TODO : 코드 문서화
    /**
     * 1000 : 틀린그림찾기
     */
    NOT_FOUND(1000, "잘못된 요청입니다.",HttpStatus.NOT_FOUND),

    /**
     * 2xxx : 레이싱게임 ..
     */
    NEED_AUTHENICATE(1000, "권한이 필요한 요청입니다,",HttpStatus.UNAUTHORIZED),


    /**
     * 9xxx : 기타 에러
     */
    INTERNAL_SERVER_ERROR(-1,"서버 내부 오류입니다.",HttpStatus.INTERNAL_SERVER_ERROR);

    private final int responseCode;
    private final String message;
    private final HttpStatus httpStatus;

}
