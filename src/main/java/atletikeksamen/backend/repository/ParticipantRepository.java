package atletikeksamen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import atletikeksamen.backend.entity.ParticipantEntity;

import java.util.List;


public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer> {
}
