package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(FindingGameWinnerId.class)
public class FindingGameWinner extends BaseEntity {
    @Id
    private Integer userId;

    @Id
    private Integer findingGameId;
}