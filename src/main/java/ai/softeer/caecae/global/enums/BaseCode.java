package ai.softeer.caecae.global.enums;

import org.springframework.http.HttpStatus;

/**
 * Http 요청에 대한 응답과 관련있는 정보
 */
public interface BaseCode {
    // 커스텀 응답 코드 종류
    int getResponseCode();
    // 응답 정보를 담은 메시지
    String getMessage();
    // HttpStatus
    HttpStatus getHttpStatus();


}



