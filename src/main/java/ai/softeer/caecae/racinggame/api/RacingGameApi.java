package ai.softeer.caecae.racinggame.api;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import ai.softeer.caecae.racinggame.domain.dto.request.PercentRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameResultRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.request.SetSelectionRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.PercentResponseDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RegisterRacingGameResultResponseDto;
import ai.softeer.caecae.racinggame.domain.dto.response.SetSelectionResponseDto;
import ai.softeer.caecae.racinggame.service.RacingGameInfoService;
import ai.softeer.caecae.racinggame.service.RacingGameParticipantService;
import ai.softeer.caecae.racinggame.service.RacingGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/racing")
public class RacingGameApi {
    private final RacingGameService racingGameService;
    private final RacingGameInfoService racingGameInfoService;
    private final RacingGameParticipantService racingGameParticipantService;


    /**
     * 레이싱 게임 종료마다 사용자의 대략적인 랭킹 반환하는 API
     *
     * @param req 사용자의 레이싱 게임 기록 정보
     */
    @GetMapping("/percent")
    public ResponseEntity<SuccessResponse<PercentResponseDto>> getPercent(PercentRequestDto req) {
        return SuccessResponse.of(SuccessCode.OK, racingGameService.getRankingPercentage(req));
    }

    /**
     * 현재 레이싱게임이 시작 가능한지 여부를 판단하는 api
     *
     * @return
     */
    @GetMapping("/available")
    public ResponseEntity<SuccessResponse<Boolean>> getIsGameStarable() {
        Boolean isGameStarable = racingGameInfoService.getIsGameStarable();
        return SuccessResponse.of(SuccessCode.OK, isGameStarable);
    }

    /**
     * 레이싱게임 결과 정보를 등록하거나 업데이트하는 api
     *
     * @param req 휴대폰번호, 기록정보
     * @return 사용자가 커스텀 선택옵션을 선택하였는지 여부
     */
    @PostMapping("/result")
    public ResponseEntity<SuccessResponse<RegisterRacingGameResultResponseDto>>
    registerOrUpdateRacingGameResult(@RequestBody RegisterRacingGameResultRequestDto req) {
        RegisterRacingGameResultResponseDto res = racingGameParticipantService.registerOrUpdateRacingGameResult(req);
        return SuccessResponse.of(SuccessCode.OK, res);
    }

    /**
     * 레이싱게임 결과 정보 등록 후, 캐스터 커스텀 선택옵션을 지정하는 api
     *
     * @param req 휴대폰 번호와 선택한 커스텀 옵션
     */
    @PostMapping("/option")
    public ResponseEntity<SuccessResponse<SetSelectionResponseDto>> setSelectionOption(@RequestBody SetSelectionRequestDto req) {
        SetSelectionResponseDto res = racingGameParticipantService.setSelectionOption(req);
        return SuccessResponse.of(SuccessCode.CREATED, res);
    }


}