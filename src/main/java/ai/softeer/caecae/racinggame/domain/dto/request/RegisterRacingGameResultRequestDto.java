package ai.softeer.caecae.racinggame.domain.dto.request;

import lombok.Builder;

// 사용자가 레이싱게임 결과를 등록하기 위한 RequestDto
@Builder
public record RegisterRacingGameResultRequestDto(
        String phone,
        double distance
) {
}
