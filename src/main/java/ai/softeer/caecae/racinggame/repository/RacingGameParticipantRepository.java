package ai.softeer.caecae.racinggame.repository;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacingGameParticipantRepository extends JpaRepository<RacingGameParticipant, Integer> {

}
