package ai.softeer.caecae.racinggame.repository;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacingGameRepository extends JpaRepository<RacingGameParticipant, Integer> {
    // TODO: 추후 캐싱 관리 필요 (with 스케쥴러)
    @Query(value = "SELECT ABS(distance - 3.15) AS adj_dist FROM racing_game_participant ORDER BY adj_dist ASC", nativeQuery = true)
    List<Double> getAdjustedDistance();
}