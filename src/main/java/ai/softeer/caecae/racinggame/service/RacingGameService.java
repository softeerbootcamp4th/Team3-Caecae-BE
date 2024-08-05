package ai.softeer.caecae.racinggame.service;

import ai.softeer.caecae.racinggame.domain.dto.request.PercentRequestDto;
import ai.softeer.caecae.racinggame.domain.dto.response.PercentResponseDto;
import ai.softeer.caecae.racinggame.repository.RacingGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RacingGameService {
    private final RacingGameRepository racingGameRepository;

    public PercentResponseDto getRankingPercentage(PercentRequestDto req) {
        double userRecord = Math.abs(req.distance() - 3.15);
        List<Double> distanceList = racingGameRepository.getAdjustedDistance();
        // 유저의 순위를 구하는 알고리즘 (lower bound)
        int lo = 0, hi = distanceList.size();
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if (distanceList.get(mid) <= userRecord) lo = mid;
            else hi = mid;
        }
        return new PercentResponseDto((double) lo / (distanceList.size() - 1) * 100);
    }
}