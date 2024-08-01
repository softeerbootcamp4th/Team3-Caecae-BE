package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class FindingGame extends BaseEntity {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int numberOfWinners;
}
