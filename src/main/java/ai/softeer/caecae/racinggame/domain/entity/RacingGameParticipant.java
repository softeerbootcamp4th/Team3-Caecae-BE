package ai.softeer.caecae.racinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import ai.softeer.caecae.user.domain.entity.User;
import jakarta.persistence.*;

@Entity
public class RacingGameParticipant extends BaseEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private double distance;

    private Integer selection;
}
