package ai.softeer.caecae.racinggame.domain.dto.response;

import lombok.Builder;

@Builder
public record PercentResponseDto(
    double percent
) {
}