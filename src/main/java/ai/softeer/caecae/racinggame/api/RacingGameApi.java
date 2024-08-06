package ai.softeer.caecae.racinggame.api;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import ai.softeer.caecae.racinggame.domain.dto.request.PercentRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.PercentResponseDto;
import ai.softeer.caecae.racinggame.service.RacingGameInfoService;
import ai.softeer.caecae.racinggame.service.RacingGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/racing")
public class RacingGameApi {
    private final RacingGameService racingGameService;
    private final RacingGameInfoService racingGameInfoService;


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
}