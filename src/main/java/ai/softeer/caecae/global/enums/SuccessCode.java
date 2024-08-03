package ai.softeer.caecae.global.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Http 요청에 대한 성공 응답과 관련있는 정보
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum SuccessCode implements BaseCode{
    //TODO : 코드 문서화
    /**
     * 1000 : 틀린그림찾기
     */
    OK(1000, "요청이 성공했습니다.",HttpStatus.OK),

    /**
     * 2xxx : 레이싱게임 ..
     */
    CREATED(1001,"생성 요청이 성공했습니다.",HttpStatus.CREATED),
    USER_CREATED(1001,"유저 회원가입이 성공했습니다.",HttpStatus.CREATED),
    TEAM_CREATED(1001,"팀 등록에 성공했습니다.",HttpStatus.CREATED);

    private final int responseCode;
    private final String message;
    private final HttpStatus httpStatus;


}
