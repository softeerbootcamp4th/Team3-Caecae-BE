package ai.softeer.caecae.racinggame.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "racingGameInfo") // redis 에 저장될 키 값, TTL 은 설정하지 않음
public class RacingGameInfo {
    @Id
    private final String id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int numberOfWinners;

    @Builder
    public RacingGameInfo(LocalDateTime startTime, LocalDateTime endTime, int numberOfWinners) {
        this.id = "racingGameInfo"; // 고정된 id 값 설정
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfWinners = numberOfWinners;
    }
}
