package ai.softeer.caecae.racinggame.repository;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameWinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacingGameWinnerRepository extends JpaRepository<RacingGameWinner, Integer> {
    List<RacingGameWinner> findAllByOrderByRankingAsc();
}