package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
public class FindingGame extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ColumnDefault("no-image")
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    @ColumnDefault("315")
    private int numberOfWinners;
}
