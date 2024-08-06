package ai.softeer.caecae.racinggame.repository;

import ai.softeer.caecae.racinggame.domain.entity.RacingGameParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacingGameParticipantRepository extends JpaRepository<RacingGameParticipant, Integer> {

}
