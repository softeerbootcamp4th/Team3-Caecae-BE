package ai.softeer.caecae.racinggame.domain.dto.request;

import java.time.LocalDateTime;

public record RegisterRacingGameRequestDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int numberOfwinners
) {
}
