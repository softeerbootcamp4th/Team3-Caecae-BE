package ai.softeer.caecae.findinggame.repository;

import ai.softeer.caecae.findinggame.domain.entity.FindingGameAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindingGameAnswerDbRepository extends JpaRepository<FindingGameAnswer, Integer> {
    public List<FindingGameAnswer> findAllByFindingGame_Id(Integer findingGameId);

}
