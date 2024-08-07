package ai.softeer.caecae.racinggame.domain.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

// 레이싱게임 정보(기간, 인원수)를 등록하는 requestDto
@Builder
public record RegisterRacingGameInfoResponseDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int numberOfWinners
) {
}
