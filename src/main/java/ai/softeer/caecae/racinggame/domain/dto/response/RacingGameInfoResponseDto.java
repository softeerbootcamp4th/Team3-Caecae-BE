package ai.softeer.caecae.racinggame.domain.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RacingGameInfoResponseDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int numberOfWinners
) {
}
