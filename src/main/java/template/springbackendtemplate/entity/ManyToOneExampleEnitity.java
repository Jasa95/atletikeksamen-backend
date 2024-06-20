package template.springbackendtemplate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ManyToOneExampleEnitity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    // en ManyToOne relation skal have EN reference til den entity som den er forbundet til.
    // Der kan være MANGE entities som refere til EN entity
    @ManyToOne
    @JoinColumn(name = "example_entity_id")
    @JsonBackReference
    private ExampleEntity exampleEntity;
}
