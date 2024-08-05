package ai.softeer.caecae.racinggame.api;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import ai.softeer.caecae.racinggame.service.RacingGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/racing")
public class RacingGameController {
    private final RacingGameService racingGameService;

    /**
     * 현재 레이싱게임이 시작 가능한지 여부를 판단하는 api
     *
     * @return
     */
    @GetMapping("/available")
    public ResponseEntity<SuccessResponse<Boolean>> getIsGameStarable() {
        Boolean isGameStarable = racingGameService.getIsGameStarable();
        return SuccessResponse.of(SuccessCode.OK, isGameStarable);
    }
}
