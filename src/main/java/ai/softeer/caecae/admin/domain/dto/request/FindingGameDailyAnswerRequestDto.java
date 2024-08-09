package ai.softeer.caecae.admin.domain.dto.request;

import ai.softeer.caecae.admin.domain.dto.FindingGameAnswerDto;
import ai.softeer.caecae.findinggame.domain.enums.AnswerType;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

// 숨은캐스퍼찾기 날짜별 정답 정보 요총 객체
@Builder
public record FindingGameDailyAnswerRequestDto(
        int dayOfEvent,
        int numberOfWinner,
        LocalTime startTime,
        LocalTime endTime,
        AnswerType answerType,
        List<FindingGameAnswerDto> answerInfoList
) {
}
