package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import ai.softeer.caecae.user.domain.entity.User;
import jakarta.persistence.*;

@Entity
@IdClass(FindingGameWinnerId.class)
public class FindingGameWinner extends BaseEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "finding_game_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private FindingGame findingGame;
}