package ai.softeer.caecae.findinggame.repository;

import ai.softeer.caecae.findinggame.domain.entity.FindingGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Redis Repository 도 만들어야 하므로 네이밍에 Db를 붙임
@Repository
public interface FindGameDbRepository extends JpaRepository<FindingGame, Integer> {
}
