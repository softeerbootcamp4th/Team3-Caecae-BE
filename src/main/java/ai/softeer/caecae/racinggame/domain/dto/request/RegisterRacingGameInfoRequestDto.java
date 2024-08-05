package ai.softeer.caecae.racinggame.domain.dto.request;

import java.time.LocalDateTime;

public record RegisterRacingGameInfoRequestDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int numberOfWinners
) {
}
