package ai.softeer.caecae.racinggame.service;

import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameRequestDto;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameInfo;
import ai.softeer.caecae.racinggame.repository.RacingGameInfoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RacingGameService {
    private final RacingGameInfoRepository racingGameInfoRepository;

    /**
     * 어드민이 레이싱게임 정보를 등록하는 로직
     *
     * @param req 입력한 레이싱게임 정보
     */
    public void registerRacingGame(RegisterRacingGameRequestDto req) {
        RacingGameInfo racingGameInfo = RacingGameInfo.builder()
                .startTime(req.startTime())
                .endTime(req.endTime())
                .numberOfWinners(req.numberOfwinners())
                .build();

        // TODO : 존재하면 저장하지 않거나 업데이트 하거나 에러 던지기. 현재는 덮어씌워짐
        racingGameInfoRepository.save(racingGameInfo);
        log.info("saved racingGameInfo: {}", racingGameInfo.getStartTime(), racingGameInfo.getEndTime());

    }

    /**
     * 현재 시각이 게임 가능한 시각인지 판단하는 로직
     *
     * @return
     */
    public Boolean getIsGameStarable() {
        RacingGameInfo racingGameInfo = racingGameInfoRepository.get();
        return LocalDateTime.now().isAfter(racingGameInfo.getStartTime())
                && LocalDateTime.now().isBefore(racingGameInfo.getEndTime());

    }
}
