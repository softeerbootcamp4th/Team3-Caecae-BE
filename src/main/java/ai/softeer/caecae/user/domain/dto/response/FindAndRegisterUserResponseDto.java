package ai.softeer.caecae.user.domain.dto.response;

import lombok.Builder;

@Builder
public record FindAndRegisterUserResponseDto(
        Integer userId
) {
}
