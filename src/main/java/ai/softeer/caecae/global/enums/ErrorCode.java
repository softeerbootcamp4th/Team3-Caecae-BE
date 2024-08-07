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
public enum ErrorCode implements BaseCode {
    //TODO : 코드 문서화 및 에러코드 음수화,
    /**
     * 1000 : 틀린그림찾기
     */
    NOT_FOUND(-1000, "잘못된 요청입니다.", HttpStatus.NOT_FOUND),

    /**
     * 2xxx : 레이싱게임
     */
    NEED_AUTHENICATE(1000, "권한이 필요한 요청입니다,", HttpStatus.UNAUTHORIZED),
    RACING_GAME_NOT_FOUND(2000, "등록된 레이싱게임이 없습니다.", HttpStatus.NOT_FOUND),

    /**
     * 3xxx : 유저
     */
    USER_NOT_FOUND(-3000, "해당 휴대폰 번호로 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),


    /**
     * 9xxx : 기타 에러
     */
    INTERNAL_SERVER_ERROR(-9000, "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int responseCode;
    private final String message;
    private final HttpStatus httpStatus;

}
