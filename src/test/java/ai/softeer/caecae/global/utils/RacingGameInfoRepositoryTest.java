package ai.softeer.caecae.global.utils;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameInfo;
import ai.softeer.caecae.racinggame.repository.RacingGameInfoRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//TODO : 테스트코드 작성할 것!
@Disabled
@SpringBootTest
public class RacingGameInfoRepositoryTest {

    @Autowired
    private RacingGameInfoRepository racingGameInfoRepository;

    @Test
    void test() {
        RacingGameInfo racingGameInfo = RacingGameInfo.builder()
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .build();


        // 저장
        racingGameInfoRepository.save(racingGameInfo);

        // `keyspace:id` 값을 가져옴
//        racingGameInfoRepository.findById(racingGameInfo.getId());

        // Person Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속한 키의 갯수를 구함
        racingGameInfoRepository.count();

        // 삭제
        racingGameInfoRepository.delete(racingGameInfo);
    }
}