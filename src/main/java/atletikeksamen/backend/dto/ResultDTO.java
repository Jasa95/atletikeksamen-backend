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
    private Date date;
    private String resultType;
    private String resultValue;
}