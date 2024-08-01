package ai.softeer.caecae.racinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class RacingGameWinner extends BaseEntity {
    @Id
    private int userId;

    @Column(nullable = false)
    private int rank;
}
