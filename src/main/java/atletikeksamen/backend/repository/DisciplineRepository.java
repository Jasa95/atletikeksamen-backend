package atletikeksamen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import atletikeksamen.backend.entity.DisciplineEntity;

import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Integer> {
    Optional<DisciplineEntity> findByName(String disciplineName);
}
