package template.springbackendtemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import template.springbackendtemplate.entity.ExampleEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExampleEntityDto {
    private int id;
    private String name;
    // flere Private variabler kan indsættes her


    public ExampleEntityDto(ExampleEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        // flere variabler kan indsættes her
    }
}
