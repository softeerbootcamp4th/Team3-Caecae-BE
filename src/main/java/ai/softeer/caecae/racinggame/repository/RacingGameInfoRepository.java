package ai.softeer.caecae.racinggame.repository;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameInfo;
import org.springframework.data.repository.CrudRepository;

//@R
public interface RacingGameInfoRepository extends CrudRepository<RacingGameInfo, Long> {
//    RedisTemplate<String, RacingGameInfo> redisTemplate;

}
