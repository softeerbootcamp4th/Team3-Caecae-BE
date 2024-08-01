package ai.softeer.caecae.findinggame.domain.entity;

import ai.softeer.caecae.user.domain.entity.User;

import java.io.Serializable;

public class FindingGameWinnerId implements Serializable {
    private User user;
    private FindingGame findingGame;

    public FindingGameWinnerId(User user, FindingGame findingGame) {
        this.user = user;
        this.findingGame = findingGame;
    }
}
