package ai.softeer.caecae.racinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RacingGameInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int numberOfWinners;
}
