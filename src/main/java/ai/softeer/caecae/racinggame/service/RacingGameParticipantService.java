package ai.softeer.caecae.racinggame.service;

import ai.softeer.caecae.global.enums.ErrorCode;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterRacingGameResultRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RegisterRacingGameResultResponseDto;
import ai.softeer.caecae.racinggame.domain.dto.request.SetSelectionRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.SetSelectionResponseDto;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import ai.softeer.caecae.racinggame.domain.exception.RacingGameException;
import ai.softeer.caecae.racinggame.repository.RacingGameParticipantRepository;
import ai.softeer.caecae.user.domain.entity.User;
import ai.softeer.caecae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RacingGameParticipantService {
    private final UserRepository userRepository;
    private final RacingGameParticipantRepository racingGameParticipantRepository;

    /**
     * 사용자가 레이싱게임 결과를 등록하는 서비스 로직
     *
     * @param req
     * @return 레이싱게임 결과 등록 후, 사용자가 커스텀 옵션을 선택하였는지 여부
     */
    @Transactional // 복합키를 가지는 두 개의 엔티티를 하나의 영속성에서 저장하기 위해 트랜잭션
    public RegisterRacingGameResultResponseDto registerOrUpdateRacingGameResult(RegisterRacingGameResultRequestDto req) {
        // phone 으로 유저를 조회하고, 존재하지 않으면 유저를 등록하여 반환하기
        User user = findOrRegisterUser(req.phone());

        // 찾은 userId 로 레이싱게임에 참여했는지 찾기
        Optional<RacingGameParticipant> optionalRacingGameParticipant = racingGameParticipantRepository.findById(user.getId());

        // 레이싱게임 참여 이력이 존재하지 않음
        if (optionalRacingGameParticipant.isEmpty()) {
            log.info(req.phone() + "의 이전 기록 : 없음");
            RacingGameParticipant saved = registerRacingGameResult(req, user);
            return RegisterRacingGameResultResponseDto.builder()
                    .isOptionSelected(false)
                    .distance(saved.getDistance())
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
                .distance(racingGameParticipant.getDistance())
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


    /**
     * 휴대폰 번호로 유저 조회하고, 존재하지 않으면 유저를 등록하여 반환하는 내부 로직
     *
     * @param phone 휴대폰 번호
     * @return 저장된 유저 엔티티
     */
    private User findOrRegisterUser(String phone) {
        Optional<User> optionalUser = userRepository.findByPhone(phone);
        return optionalUser.orElseGet(() -> registerUser(phone));
    }

    /**
     * 휴대폰 번호로 유저를 등록하는 내부 로직
     *
     * @param phone 휴대폰 번호
     * @return 저장된 유저 엔티티
     */
    private User registerUser(String phone) {
        User user = User.builder()
                .phone(phone)
                .build();
        return userRepository.save(user);
    }

    /**
     * 사용자가 선택한 번호로 캐스퍼 커스텀 옵션을 저장하는 로직
     *
     * @param req 휴대폰 번호, 선택 옵션
     */
    public SetSelectionResponseDto setSelectionOption(SetSelectionRequestDto req) {
        User user = userRepository.findByPhone(req.phone())
                .orElseThrow(() -> new RacingGameException(ErrorCode.USER_NOT_FOUND));

        RacingGameParticipant racingGameParticipant = racingGameParticipantRepository.findById(user.getId())
                .orElseThrow(() -> new RacingGameException(ErrorCode.USER_NOT_FOUND));

        // 사용자가 선택한 커스텀 정보 업데이트
        racingGameParticipant.setSelection(req.selection());
        RacingGameParticipant saved = racingGameParticipantRepository.save(racingGameParticipant);

        return SetSelectionResponseDto.builder()
                .phone(req.phone())
                .selection(saved.getSelection())
                .build();
    }
}
