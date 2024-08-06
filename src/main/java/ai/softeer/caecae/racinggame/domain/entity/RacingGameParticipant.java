package ai.softeer.caecae.racinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import ai.softeer.caecae.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RacingGameParticipant extends BaseEntity {
    @Id
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private double distance;

    private Integer selection;

    // 315km 와의 차이를 반환하는 메서드
    public double getAbsoluteDistance() {
        return Math.abs(315 - distance);
    }

    // 커스텀 옵션이 선택되었는지 여부를 반환하는 메서드
    public Boolean isOptionSelected() {
        return selection != null;
    }

    // 엔티티의 점수(거리)를 업데이트하는 메서드
    public void updateDistance(double distance) {
        this.distance = distance;
    }
}