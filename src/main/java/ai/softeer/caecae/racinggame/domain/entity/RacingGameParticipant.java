package ai.softeer.caecae.racinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RacingGameParticipant extends BaseEntity {
    @Id
    private Integer userId;

    @Column(nullable = false)
    private double distance;

    private Integer selection;
}
