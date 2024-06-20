package atletikeksamen.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResultDTO {
    private Integer id;
    private Integer disciplineId;
    private Integer participantId;
    private String participantName;
    private String disciplineName;
    private String resultValue;
    private String resultType;
    private Date date;
}
