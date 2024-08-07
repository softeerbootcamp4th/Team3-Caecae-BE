package ai.softeer.caecae.admin.api;

import ai.softeer.caecae.admin.domain.dto.response.DrawResponseDto;
import ai.softeer.caecae.admin.service.AdminService;
import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameInfoRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RacingGameInfoResponseDto;
import ai.softeer.caecae.racinggame.service.RacingGameInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // TODO : 전역에 적용되도록 수정
@RequiredArgsConstructor
public class AdminController {
    private final RacingGameInfoService racingGameService;
    private final AdminService adminService;

    /**
     * 관리자가 등록된 레이싱게임의 정보를 조회하는 로직
     *
     * @return 레이싱게임 정보
     */
    @GetMapping("/racing/period")
    public ResponseEntity<SuccessResponse<RacingGameInfoResponseDto>> getRacingGameInfo() {
        RacingGameInfoResponseDto racingGameInfoDto = racingGameService.getRacingGameInfo();
        return SuccessResponse.of(SuccessCode.OK, racingGameInfoDto);
    }

    /**
     * 관리자가 레이싱게임의 시작시간, 종료시간, 당첨인원을 설정하는 api
     *
     * @param req 레이싱게임 정보
     * @return
     */
    @PostMapping("/racing/period")
    public ResponseEntity<SuccessResponse<Object>> registerRacingGame(@RequestBody RegisterRacingGameInfoRequestDto req) {
        racingGameService.registerRacingGameInfo(req);
        return SuccessResponse.of(SuccessCode.RACING_GAME_CREATED);
    }

    @PostMapping("/racing/draw")
    public ResponseEntity<SuccessResponse<List<DrawResponseDto>>> drawRacingGameWinner() {
        return SuccessResponse.of(SuccessCode.OK, adminService.drawRacingGameWinner());
    }
}
