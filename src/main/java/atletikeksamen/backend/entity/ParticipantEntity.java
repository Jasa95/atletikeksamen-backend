package atletikeksamen.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// håndtere bi-direktionelle relationer på er ved at bruge @JsonIdentityInfo, som vil bruge ID'er til at undgå uendelige løkker
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String club;

    @ManyToMany
    @JoinTable(
            name = "participants_discipline",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    @JsonManagedReference
    private List<DisciplineEntity> disciplines = new ArrayList<>();

    public void addDiscipline(DisciplineEntity discipline) {
        this.disciplines.add(discipline);
    }

    public void removeDiscipline(DisciplineEntity discipline) {
        this.disciplines.remove(discipline);
    }
}
