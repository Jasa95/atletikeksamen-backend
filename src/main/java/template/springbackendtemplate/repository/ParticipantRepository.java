package template.springbackendtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.springbackendtemplate.entity.ParticipantEntity;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer> {
}
