package ai.softeer.caecae.findinggame.service;

import ai.softeer.caecae.findinggame.domain.entity.FindingGame;
import ai.softeer.caecae.findinggame.domain.enums.AnswerType;
import ai.softeer.caecae.findinggame.repository.FindGameDbRepository;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterFindingGamePeriodRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RegisterFindingGamePeriodResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindingGameService {
    private final FindGameDbRepository findGameDbRepository;

    /**
     * 어드민이 숨은캐스퍼찾기 게임 기간을 등록하는 로직
     *
     * @param req 게임 시작 날짜
     * @return 게임 시작 날짜, 종료 날짜(+6일)
     */
    @Transactional
    public RegisterFindingGamePeriodResponseDto registerFindingGamePeriod(RegisterFindingGamePeriodRequestDto req) {
        List<FindingGame> findingGames = findGameDbRepository.findAll();
        // 등록된 게임 정보가 없으면 생성하기
        if (findingGames.isEmpty()) {
            findingGames = initFindingGames();
        }

        // 게임 정보 기간 업데이트
        LocalDate date = req.startDate();
        for (FindingGame findingGame : findingGames) {
            findingGame.updateFindingGamePeriod(
                    date.atTime(15, 15),
                    date.plusDays(1).atTime(14, 15)
            );
            date = date.plusDays(1);
        }


        findGameDbRepository.saveAll(findingGames);

        return RegisterFindingGamePeriodResponseDto.builder()
                .startDate(req.startDate())
                .endDate(req.startDate().plusDays(6))
                .build();
    }

    // 7개의 숨은캐스퍼찾기 게임 정보 객체 초기화
    private List<FindingGame> initFindingGames() {
        List<FindingGame> findingGames = new ArrayList<>();
        for (int day = 0; day < 7; day++) {
            findingGames.add(
                    FindingGame.builder()
                            .imageUrl("no-image")
                            .numberOfWinners(315)
                            .answerType(AnswerType.UNSELECTED)
                            .build());
        }
        return findingGames;
    }
}
