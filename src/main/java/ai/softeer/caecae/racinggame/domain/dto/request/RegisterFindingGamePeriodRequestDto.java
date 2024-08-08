package ai.softeer.caecae.racinggame.domain.dto.request;

import lombok.Builder;

import java.time.LocalDate;

// 어드민이 숨은캐스퍼찾기 게임 기간을 등록할 때 사용
@Builder
public record RegisterFindingGamePeriodRequestDto(
        LocalDate startDate
) {
}
