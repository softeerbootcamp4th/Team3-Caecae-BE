package ai.softeer.caecae.racinggame.service;

import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameResultRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RegisterRacingGameResultResponseDto;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import ai.softeer.caecae.racinggame.repository.RacingGameParticipantRepository;
import ai.softeer.caecae.user.domain.dto.request.FindAndRegisterUserRequestDto;
import ai.softeer.caecae.user.domain.entity.User;
import ai.softeer.caecae.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RacingGameParticipantService {
    private final UserService userService;
    private final RacingGameParticipantRepository racingGameParticipantRepository;

    /**
     * 사용자가 레이싱게임 결과를 등록하는 서비스 로직
     *
     * @param req
     * @return
     */
    public RegisterRacingGameResultResponseDto registerOrUpdateRacingGameResult(RegisterRacingGameResultRequestDto req) {
        // UserRepository 에서 사용자를 조회, 없으면 등록하고 해당 유저의 userId를 반환
        User user = userService.findAndRegisterUser(FindAndRegisterUserRequestDto.builder()
                .phone(req.phone())
                .build());

        // 찾은 userId 로 레이싱게임에 참여했는지 찾기
        Optional<RacingGameParticipant> optionalRacingGameParticipant = racingGameParticipantRepository.findById(user.getId());

        // 레이싱게임 참여 이력이 존재하지 않음
        if (optionalRacingGameParticipant.isEmpty()) {
            log.info(req.phone() + "의 이전 기록 : 없음");
            registerRacingGameResult(req, user);
            return RegisterRacingGameResultResponseDto.builder()
                    .isOptionSelected(false)
                    .build();
        }

        // 레이싱게임 참여 이력이 존재함
        RacingGameParticipant racingGameParticipant = optionalRacingGameParticipant.get();
        log.info(req.phone() + "의 이전 기록 :" + racingGameParticipant.getDistance());

        // 점수가 더 높은경우 (차이가 더 작은 경우) 데이터를 갱신함
        // TODO : 315를 constant화
        if (racingGameParticipant.getAbsoluteDistance() > Math.abs(315 - req.distance())) {
            racingGameParticipant.updateDistance(req.distance());
            racingGameParticipantRepository.save(racingGameParticipant);
        }

        return RegisterRacingGameResultResponseDto.builder()
                .isOptionSelected(racingGameParticipant.isOptionSelected())
                .build();
    }

    /**
     * 사용자가 레이싱게임결과를 신규로 등록하는 로직
     *
     * @param req 전화번호, 레이싱게임의 결과 distance
     * @return 등록된 레이싱게임결과 엔티티
     */

    private RacingGameParticipant registerRacingGameResult(RegisterRacingGameResultRequestDto req, User user) {
        RacingGameParticipant racingGameParticipant = RacingGameParticipant.builder()
                .distance(req.distance())
                .user(user)
                .build();
        return racingGameParticipantRepository.save(racingGameParticipant);
    }
}
