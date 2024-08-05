package ai.softeer.caecae.admin.api;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameRequestDto;
import ai.softeer.caecae.racinggame.service.RacingGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final RacingGameService racingGameService;

    /**
     * 관리자가 레이싱게임의 시작시간, 종료시간, 당첨인원을 설정하는 api
     *
     * @param req 레이싱게임 정보
     * @return
     */
    @PostMapping("/racing/period")
    public ResponseEntity<SuccessResponse<Object>> registerRacingGame(@RequestBody RegisterRacingGameRequestDto req) {
        racingGameService.registerRacingGame(req);
        return SuccessResponse.of(SuccessCode.RACING_GAME_CREATED);
    }
}
