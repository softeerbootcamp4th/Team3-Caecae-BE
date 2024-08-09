package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindingGameAnswer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public void updateFindingGame(int coordX, int coordY, String descriptionImageUrl, String title, String content) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.descriptionImageUrl = descriptionImageUrl;
        this.title = title;
        this.content = content;
    }
}
