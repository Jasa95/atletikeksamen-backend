package template.springbackendtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.springbackendtemplate.entity.ResultsEntity;

public interface ResultsRepository extends JpaRepository<ResultsEntity, Integer> {
}
