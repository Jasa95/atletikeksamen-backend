package atletikeksamen.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
public class DisciplineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String resultsType;

    @ManyToMany(mappedBy = "disciplines")
    @JsonBackReference
    private List<ParticipantEntity> participants = new ArrayList<>();

    public void addParticipant(ParticipantEntity participant) {
        this.participants.add(participant);
    }

    public void removeParticipant(ParticipantEntity participant) {
        this.participants.remove(participant);
    }

}

