package ai.softeer.caecae.racinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import ai.softeer.caecae.user.domain.entity.User;
import jakarta.persistence.*;

@Entity
public class RacingGameWinner extends BaseEntity {
    @Id
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private int ranking;
}