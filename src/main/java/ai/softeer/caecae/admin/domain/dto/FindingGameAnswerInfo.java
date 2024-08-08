package ai.softeer.caecae.admin.domain.dto;

import ai.softeer.caecae.findinggame.domain.enums.AnswerType;
import lombok.Builder;

// 숨은캐스퍼찾기 정답 정보
@Builder
public record FindingGameAnswerInfo(
        int coordX,
        int coodY,
        String descriptionImageUrl,
        String title,
        String content,
        AnswerType answerType
) {
}
