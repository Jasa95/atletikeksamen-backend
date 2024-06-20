package template.springbackendtemplate.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// håndtere bi-direktionelle relationer på er ved at bruge @JsonIdentityInfo, som vil bruge ID'er til at undgå uendelige løkker
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ResultsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String resultType;
    private Date date;
    private String resultValue;



    @ManyToOne
    private ParticipantEntity participantEntity;
    @ManyToOne
    private DisciplineEntity disciplineEntity;

}
