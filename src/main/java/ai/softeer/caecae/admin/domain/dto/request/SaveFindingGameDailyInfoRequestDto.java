package ai.softeer.caecae.admin.domain.dto.request;

import ai.softeer.caecae.admin.domain.dto.FindingGameAnswerInfo;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

// 숨은캐스퍼찾기 날짜별 정답 정보 요총 객체
@Builder
public record SaveFindingGameDailyInfoRequestDto(
        int dayOfEvent,
        int numberOfWinner,
        LocalTime startTime,
        LocalTime endTime,
        List<FindingGameAnswerInfo> answerInfoList
) {
}
