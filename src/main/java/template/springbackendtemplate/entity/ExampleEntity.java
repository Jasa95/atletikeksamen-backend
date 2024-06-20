package template.springbackendtemplate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    // flere Private variabler kan indsættes her


    /*
    OneToMany relationen skal have en Liste af ManyToOne enitites
    mappedby angiver hvilken property ManyToOneExampleEntity'en har, som er forbundet til denne entity
    i dette tilfælde er det "exampleEntity" property'en fra ManyToOneExampleEntity
    Det er omvendt af ManyToOne, så der kan være mange refences til denne entity
    */
    @OneToMany(mappedBy = "exampleEntity")
    @JsonManagedReference
    private List<ManyToOneExampleEnitity> manyToOneExampleEnitities;

}
