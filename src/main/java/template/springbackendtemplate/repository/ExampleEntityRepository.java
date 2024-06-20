package template.springbackendtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.springbackendtemplate.entity.ExampleEntity;

// JpaRepository er en interface som giver os mange metoder til at interagere med databasen
// Typen skal matche den entity som vi vil interagere med i databasen, og Integer er typen af ID (kan være string hvis det er det man har sat id til)
// Vi kan bruge den til at lave queries, gemme, slette og opdatere entities
public interface ExampleEntityRepository extends JpaRepository<ExampleEntity, Integer> {
}
