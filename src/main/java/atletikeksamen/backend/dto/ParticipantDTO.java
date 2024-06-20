package atletikeksamen.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParticipantDTO {
    private Integer id;
    private String name;
    private int age;
    private String gender;
    private String club;
    private List<String> disciplineNames;
}
