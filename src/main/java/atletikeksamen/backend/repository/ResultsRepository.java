package atletikeksamen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import atletikeksamen.backend.entity.ResultsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultsRepository extends JpaRepository<ResultsEntity, Integer> {
    List<ResultsEntity> findByDisciplineId(Integer disciplineId);
}
