package template.springbackendtemplate.api;

import org.springframework.web.bind.annotation.*;
import template.springbackendtemplate.entity.ExampleEntity;
import template.springbackendtemplate.service.ExampleEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/example-entity")

public class ExampleController {
    private final ExampleEntityService exampleEntityService;

    public ExampleController(ExampleEntityService exampleEntityService) {
        this.exampleEntityService = exampleEntityService;
    }

    /*
    Her kan der indsættes metoder som skal bruges i controlleren
    Du kan lave metoder som henter data fra service klassen
    Du kan lave metoder som sender data til service klassen
    Du kan lave metoder som sletter data fra service klassen
    Du kan lave metoder som opdaterer data i service klassen
     */

    // Metode som henter alle entities fra service klassen
    @GetMapping
    public List<ExampleEntity> getAllExampleEntities() {
        return exampleEntityService.getAllExampleEntities();
    }

    // Metode som henter en entity fra service klassen baseret på ID
    @GetMapping("/{id}")
    public ExampleEntity getExampleEntityById(@PathVariable int id) {
        return exampleEntityService.getExampleEntityById(id);
    }

    // Metode som sender en entity til service klassen for at gemme i databasen
    @PostMapping
    public ExampleEntity saveExampleEntity(@RequestBody ExampleEntity exampleEntity) {
        return exampleEntityService.saveExampleEntity(exampleEntity);
    }

    // Metode som sender et ID til service klassen for at slette en entity fra databasen
    @DeleteMapping("/{id}")
    public void deleteExampleEntity(@PathVariable int id) {
        exampleEntityService.deleteExampleEntity(id);
    }

    // Metode som sender et ID og en entity til service klassen for at opdatere en entity i databasen
    @PutMapping("/{id}")
    public ExampleEntity updateExampleEntity(@PathVariable int id, @RequestBody ExampleEntity exampleEntity) {
        return exampleEntityService.updateExampleEntity(id, exampleEntity);
    }
}
