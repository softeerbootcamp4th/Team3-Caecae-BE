package ai.softeer.caecae.admin.service;

import ai.softeer.caecae.admin.domain.dto.FindingGameAnswerDto;
import ai.softeer.caecae.admin.domain.dto.request.FindingGameDailyAnswerRequestDto;
import ai.softeer.caecae.admin.domain.dto.response.FindingGameDailyAnswerResponseDto;
import ai.softeer.caecae.admin.domain.dto.response.RacingGameWinnerResponseDto;
import ai.softeer.caecae.admin.domain.exception.AdminException;
import ai.softeer.caecae.findinggame.domain.entity.FindingGame;
import ai.softeer.caecae.findinggame.domain.entity.FindingGameAnswer;
import ai.softeer.caecae.findinggame.domain.enums.AnswerType;
import ai.softeer.caecae.findinggame.repository.FindingGameAnswerDbRepository;
import ai.softeer.caecae.findinggame.repository.FindingGameDbRepository;
import ai.softeer.caecae.global.enums.ErrorCode;
import ai.softeer.caecae.racinggame.domain.dto.request.RegisterFindingGamePeriodRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.RegisterFindingGamePeriodResponseDto;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameWinner;
import ai.softeer.caecae.racinggame.repository.RacingGameInfoRepository;
import ai.softeer.caecae.racinggame.repository.RacingGameRepository;
import ai.softeer.caecae.racinggame.repository.RacingGameWinnerRepository;
import ai.softeer.caecae.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final RacingGameInfoRepository raceGameInfoRepository;
    private final RacingGameRepository racingGameRepository;
    private final RacingGameWinnerRepository racingGameWinnerRepository;
    private final FindingGameDbRepository findingGameDbRepository;
    private final FindingGameAnswerDbRepository findingGameAnswerDbRepository;

    /**
     * 당첨자를 뽑는 서비스 로직
     *
     * @return 당첨자 리스트
     */
    @Transactional
    public List<RacingGameWinnerResponseDto> drawRacingGameWinner() {
        List<RacingGameWinnerResponseDto> racingGameWinnerResponseDtoList = new ArrayList<>();
        List<RacingGameParticipant> participants = racingGameRepository.findAllByAdjustedDistance(315.0);
        List<RacingGameWinner> winners = new ArrayList<>();

        int n = participants.size();
        double[] accumulateSector = {0.2, 0.8, 1.5, 3.0, 5.0, 10.0, 20.0, 35.0, 50.0, 1e9}; // 1e9: inf를 의미
        int[] weight = {250, 180, 125, 100, 60, 30, 15, 10, 7, 3};
        int selectionWeight = 10, weightSum = Arrays.stream(weight).sum() + 10 * weight.length;
        int currentSector = 0, idx = 0; // 현 순위가 속하는 구간을 가르키는 포인터, 현 참여자 인덱스
        double accumulatedPercentPoint = 100.0 / participants.size(), accumulatedPercent = 0;
        int[] arr = new int[participants.size()]; // 각 참여자의 가중치 배열
        // 가중치 배열에 가중치를 넣어주는 과정
        for (RacingGameParticipant p : participants) {
            accumulatedPercent += accumulatedPercentPoint;
            while (accumulatedPercent > accumulateSector[currentSector] + 0.01) currentSector++;
            arr[idx++] = weight[currentSector] + (p.getSelection() != 0 ? selectionWeight : 0);
        }
        // N명 중에서 한 명을 선택한 후, 그 사람을 가중치 / 전체 가중치 확률로 당첨자로 만든다. 이를 당첨인원수만큼 반복
        int drawNumber = Math.min(315, participants.size()), ranking = 1; // TODO: 315 Global 변수화
        while (ranking <= drawNumber) {
            int cur = (int) (Math.random() * n + 0.5) % n;
            if (arr[cur] < 0) continue;
            double poss = Math.random();
            if (poss <= (double) arr[cur] / weightSum) {
                RacingGameParticipant p = participants.get(cur);
                User user = p.getUser();
                racingGameWinnerResponseDtoList.add(RacingGameWinnerResponseDto.builder()
                        .ranking(ranking)
                        .phone(user.getPhone())
                        .distance(p.getDistance())
                        .selection(p.getSelection())
                        .build());
                winners.add(RacingGameWinner.builder()
                        .userId(p.getUserId())
                        .ranking(ranking)
                        .build());
                arr[cur] = -1;
                ranking++;
            }
        }
        // TODO: 수학적으로 보이기 + 더 나은 방법 생각해보기?
        racingGameWinnerRepository.saveAll(winners);
        return racingGameWinnerResponseDtoList;
    }

    /**
     * 당첨자 리스트를 가져오는 서비스 로직
     *
     * @return 당첨자 리스트
     */
    public List<RacingGameWinnerResponseDto> getRacingGameWinner() {
        List<RacingGameWinner> winners = racingGameWinnerRepository.findAllByOrderByRankingAsc();
        List<RacingGameWinnerResponseDto> WinnerResponseDtoList = new ArrayList<>();
        for (RacingGameWinner winner : winners) {
            RacingGameParticipant p = racingGameRepository.findById(winner.getUserId()).get();
            WinnerResponseDtoList.add(RacingGameWinnerResponseDto.builder()
                    .ranking(winner.getRanking())
                    .phone(winner.getUser().getPhone())
                    .distance(p.getDistance())
                    .selection(p.getSelection())
                    .build());
        }
        return WinnerResponseDtoList;
    }

    /**
     * 숨은캐스퍼찾기 게임 날짜별 정답 정보, 게임시작시간, 종료시간, 당첨인원수를 업데이트하는 로직
     *
     * @param req
     * @return
     */
    public FindingGameDailyAnswerResponseDto saveFindingGameDailyAnswer
    (FindingGameDailyAnswerRequestDto req) {

        // TODO : req.day 가 1~7인지 검증
        FindingGame findingGame = findingGameDbRepository
                // FindingGame 테이블의 1~7번째 열에 데이터가 들어간다고 가정하고, dayOfEvent 를 id로 활용하여 조회함
                // 좋은 방식은 아닌 것 같아서, 추후 어떻게 할지 논의 하면 좋겠음.
                .findById(req.dayOfEvent()).orElseThrow(() -> new AdminException(ErrorCode.FINDING_GAME_OF_DAY_NOT_FOUND));

        // fingingGame의 시작시간, 종료시간, 당첨자수, 정답타입 새로운 정보로 업데이트
        findingGame.updateFindingGamePeriod(
                findingGame.getStartTime().with(req.startTime()),
                findingGame.getEndTime().with(req.endTime()),
                req.numberOfWinner(),
                req.answerType()
        );
        findingGameDbRepository.save(findingGame);

        // findingGame의 2개의 정담(findingGameAnswer) 정보를 업데이트
        List<FindingGameAnswer> findingGameAnswerList = findingGameAnswerDbRepository
                .findAllByFindingGame_Id(req.dayOfEvent());

        // findingGameAnswer 정보가 존재하지 않는다면 초기화
        if (findingGameAnswerList.isEmpty()) {
            findingGameAnswerList = initFindingGameAnswer(findingGame);
        }

        // 2개의 정답 정보를 request에 들어온 대로 업데이트
        for (int idx = 0; idx < 2; idx++) {
            FindingGameAnswerDto findingGameAnswerDto = req.answerInfoList().get(idx);
            FindingGameAnswer findingGameAnswer = findingGameAnswerList.get(idx);

            findingGameAnswer.updateFindingGame(
                    findingGameAnswerDto.coordX(),
                    findingGameAnswerDto.coordY(),
                    findingGameAnswerDto.descriptionImageUrl(),
                    findingGameAnswerDto.title(),
                    findingGameAnswerDto.content()
            );
        }

        findingGameAnswerDbRepository.saveAll(findingGameAnswerList);

        return FindingGameDailyAnswerResponseDto.builder()
                .dayOfEvent(req.dayOfEvent())
                .numberOfWinner(req.numberOfWinner())
                .startTime(req.startTime())
                .endTime(req.endTime())
                .answerInfoList(req.answerInfoList())
                .build();


    }

    private List<FindingGameAnswer> initFindingGameAnswer(FindingGame findingGame) {
        List<FindingGameAnswer> findingGameAnswerList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            findingGameAnswerList.add(FindingGameAnswer.builder()
                    .coordX(-1)
                    .coordY(-1)
                    .descriptionImageUrl("no-image")
                    .title("no-title")
                    .content("no-content")
                    .findingGame(findingGame)
                    .build());
        }
        return findingGameAnswerList;
    }

    /**
     * 어드민이 숨은캐스퍼찾기 게임 기간을 등록하는 로직
     *
     * @param req 게임 시작 날짜
     * @return 게임 시작 날짜, 종료 날짜(+6일)
     */
    @Transactional
    public RegisterFindingGamePeriodResponseDto registerFindingGamePeriod(RegisterFindingGamePeriodRequestDto req) {
        List<FindingGame> findingGames = findingGameDbRepository.findAll();
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


        findingGameDbRepository.saveAll(findingGames);

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
                            .build()
            );
        }
        return findingGames;
    }
}
