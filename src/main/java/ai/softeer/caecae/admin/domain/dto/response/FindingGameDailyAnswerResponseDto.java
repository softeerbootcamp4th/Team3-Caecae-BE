package ai.softeer.caecae.admin.domain.dto.response;

import ai.softeer.caecae.admin.domain.dto.FindingGameAnswerDto;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

// 숨은캐스퍼찾기 날짜별 정답 정보 응답 객체
@Builder
public record FindingGameDailyAnswerResponseDto(
        int dayOfEvent,
        int numberOfWinner,
        LocalTime startTime,
        LocalTime endTime,
        List<FindingGameAnswerDto> answerInfoList
) {
}
