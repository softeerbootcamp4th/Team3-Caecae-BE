package ai.softeer.caecae.racinggame.domain.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RegisterRacingGameInfoRequestDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int numberOfWinners
) {
}
