package ai.softeer.caecae.admin.domain.dto;

import lombok.Builder;

// 숨은캐스퍼찾기 정답 정보
@Builder
public record FindingGameAnswerDto(
        int coordX,
        int coordY,
        String descriptionImageUrl,
        String title,
        String content
) {
}
