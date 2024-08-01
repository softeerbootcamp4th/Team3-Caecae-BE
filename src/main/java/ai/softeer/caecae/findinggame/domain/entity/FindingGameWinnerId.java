package ai.softeer.caecae.findinggame.domain.entity;

import java.io.Serializable;

public class FindingGameWinnerId implements Serializable {
    private Integer userId;
    private Integer findingGameId;

    public FindingGameWinnerId(Integer userId, Integer findingGameId) {
        this.userId = userId;
        this.findingGameId = findingGameId;
    }
}
