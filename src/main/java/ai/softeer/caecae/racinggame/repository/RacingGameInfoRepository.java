package ai.softeer.caecae.racinggame.repository;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class RacingGameInfoRepository {
    // 레디스에 저장될 레이싱게임 정보의 키
    private final static String KEY = "racingGameInfo";
    private final RedisTemplate<String, Object> redisTemplate;

    // 레디스에 저장하는 로직
    public void save(RacingGameInfo gameInfo) {
        redisTemplate.opsForValue().set(KEY, gameInfo);
    }

    // 레디스에 저장된 객체를 가져오는 로직
    public RacingGameInfo get() {
        // TODO : 만약 없으면?
        return (RacingGameInfo) redisTemplate.opsForValue().get(KEY);
    }
}
