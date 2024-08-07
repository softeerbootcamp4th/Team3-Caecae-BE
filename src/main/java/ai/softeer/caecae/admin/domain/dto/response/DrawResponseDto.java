package ai.softeer.caecae.admin.domain.dto.response;

import lombok.Builder;

@Builder
public record DrawResponseDto(
    int ranking,
    String phone,
    double distance,
    Integer selection
) {
}
