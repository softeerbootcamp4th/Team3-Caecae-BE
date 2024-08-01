package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class FindingGameAnswer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int coordX;

    @Column(nullable = false)
    private int coordY;

    @Column(nullable = false)
    private String descriptionImageUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "finding_game_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private FindingGame findingGame;
}
