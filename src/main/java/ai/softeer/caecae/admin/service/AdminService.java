package ai.softeer.caecae.admin.service;

import ai.softeer.caecae.admin.domain.dto.response.RacingGameWinnerResponseDto;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import ai.softeer.caecae.racinggame.domain.entity.RacingGameWinner;
import ai.softeer.caecae.racinggame.repository.RacingGameInfoRepository;
import ai.softeer.caecae.racinggame.repository.RacingGameRepository;
import ai.softeer.caecae.racinggame.repository.RacingGameWinnerRepository;
import ai.softeer.caecae.user.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final RacingGameInfoRepository raceGameInfoRepository;
    private final RacingGameRepository racingGameRepository;
    private final RacingGameWinnerRepository racingGameWinnerRepository;

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
}
