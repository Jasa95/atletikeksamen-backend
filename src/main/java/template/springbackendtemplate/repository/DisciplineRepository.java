package template.springbackendtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.springbackendtemplate.entity.DisciplineEntity;

public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Integer> {
}
