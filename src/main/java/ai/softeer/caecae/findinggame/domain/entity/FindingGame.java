package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.findinggame.domain.enums.AnswerType;
import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindingGame extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //Bulk insert를 위한 pk type 설정
    private Integer id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int numberOfWinners;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    // 숨은 캐스퍼찾기 기간 업데이트
    public FindingGame updateFindingGamePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        return this;
    }

    // 숨은 캐스퍼찾기 기간, 기타 정보 업데이트
    public FindingGame updateFindingGamePeriod(LocalDateTime startTime, LocalDateTime endTime,
                                               int numberOfWinners, AnswerType answerType) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfWinners = numberOfWinners;
        this.answerType = answerType;
        return this;
    }
}
