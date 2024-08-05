package ai.softeer.caecae.racinggame.api;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import ai.softeer.caecae.racinggame.domain.dto.request.PercentRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.PercentResponseDto;
import ai.softeer.caecae.racinggame.service.RacingGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/racing")
public class RacingGameApi {
    private final RacingGameService racingGameService;

    @GetMapping("/percent")
    public ResponseEntity<SuccessResponse<PercentResponseDto>> getPercent(PercentRequestDto req) {
        return SuccessResponse.of(SuccessCode.OK, racingGameService.getRankingPercentage(req));
    }
}