package atletikeksamen.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisciplineDTO {
    private Integer id;
    private String name;
    private String resultsType;
    private List<String> participantNames;
}