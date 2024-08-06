package ai.softeer.caecae.racinggame.service;

import ai.softeer.caecae.global.enums.ErrorCode;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameInfoRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RacingGameInfoResponseDto;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameInfo;
import ai.softeer.caecae.racinggame.domain.exception.RacingGameException;
import ai.softeer.caecae.racinggame.repository.RacingGameInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class RacingGameInfoServiceTest {

    @InjectMocks
    private RacingGameInfoService racingGameInfoService;

    @Mock
    private RacingGameInfoRepository racingGameInfoRepository;

    @Test
    @DisplayName("등록된 레이싱게임 정보를 조회하기 : 성공")
    void getRacingGameInfo_성공() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minusDays(1);
        LocalDateTime endTime = now.plusDays(1);

        RacingGameInfo racingGameInfo = RacingGameInfo.builder()
                .startTime(startTime)
                .endTime(endTime)
                .numberOfWinners(315)
                .build();

        Mockito.when(racingGameInfoRepository.get()).thenReturn(racingGameInfo);

        // when
        RacingGameInfoResponseDto racingGameInfoResult = racingGameInfoService.getRacingGameInfo();

        // then
        Assertions.assertThat(racingGameInfoResult).isNotNull();
        Assertions.assertThat(racingGameInfoResult.startTime()).isEqualTo(startTime);
        Assertions.assertThat(racingGameInfoResult.endTime()).isEqualTo(endTime);
        Assertions.assertThat(racingGameInfoResult.numberOfWinners()).isEqualTo(315);
    }

    @Test
    @DisplayName("등록된 레이싱게임 정보를 조회하기 : 등록된 정보가 없는 경우 실패")
    void getRacingGameInfo_실패() {
        // given
        Mockito.when(racingGameInfoRepository.get()).thenReturn(null);

        // when & then
        Assertions.assertThatThrownBy(() -> racingGameInfoService.getRacingGameInfo())
                .isInstanceOf(RacingGameException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.RACING_GAME_NOT_FOUND);

    }

    @Test
    @DisplayName("어드민이 레이싱게임 정보를 등록하기 - 성공")
    void registerRacingGameInfo() {
        //TODO : 예외처리 후 테스트코드 작성

        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minusDays(1);
        LocalDateTime endTime = now.plusDays(1);


        RegisterRacingGameInfoRequestDto registerRacingGameInfoRequestDto = RegisterRacingGameInfoRequestDto.builder()
                .startTime(startTime)
                .endTime(endTime)
                .numberOfWinners(300)
                .build();

        Mockito.doNothing().when(racingGameInfoRepository).save(Mockito.any(RacingGameInfo.class));

        // when & then
        racingGameInfoService.registerRacingGameInfo(registerRacingGameInfoRequestDto);
    }

    @Test
    @DisplayName("현재 시각을 기준으로 레이싱게임이 시작 가능한지 테스트 - 시작가능")
    void getIsGameStarable_시작가능() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minusDays(1);
        LocalDateTime endTime = now.plusDays(1);

        RacingGameInfo racingGameInfo = RacingGameInfo.builder()
                .startTime(startTime)
                .endTime(endTime)
                .numberOfWinners(315)
                .build();

        Mockito.when(racingGameInfoRepository.get()).thenReturn(racingGameInfo);

        // when
        Boolean isGameStarable = racingGameInfoService.getIsGameStarable();

        // then
        Assertions.assertThat(isGameStarable).isTrue();
    }

    @Test
    @DisplayName("현재 시각을 기준으로 레이싱게임이 시작 가능한지 테스트 - 시작불가")
    void getIsGameStarable_시작불가() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(1);
        LocalDateTime endTime = now.plusDays(12);

        RacingGameInfo racingGameInfo = RacingGameInfo.builder()
                .startTime(startTime)
                .endTime(endTime)
                .numberOfWinners(315)
                .build();

        Mockito.when(racingGameInfoRepository.get()).thenReturn(racingGameInfo);

        // when
        Boolean isGameStarable = racingGameInfoService.getIsGameStarable();

        // then
        Assertions.assertThat(isGameStarable).isFalse();
    }
}