package ai.softeer.caecae.racinggame.domain.dto.response;

import lombok.Builder;

// 사용자가 레이싱게임 결과를 등록하기 위한 RequestDto
@Builder
public record RegisterRacingGameResultResponseDto(
        // 시용자가 커스텀옵션을 이전에 선택하였는지 여부
        Boolean isOptionSelected
) {
}
