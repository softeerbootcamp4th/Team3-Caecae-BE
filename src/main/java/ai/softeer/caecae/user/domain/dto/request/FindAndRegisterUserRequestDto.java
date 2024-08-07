package ai.softeer.caecae.user.domain.dto.request;

import lombok.Builder;

@Builder
public record FindAndRegisterUserRequestDto(
        String phone
) {
}
