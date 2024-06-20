package template.springbackendtemplate.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import template.springbackendtemplate.entity.ExampleEntity;
import template.springbackendtemplate.repository.ExampleEntityRepository;

import java.util.List;

@Service
public class ExampleEntityService {
    // skal bruge et repository for at kunne interagere med databasen
    // Laver vores repository til en final variabel, da det skal være den samme gennem hele klassen
    private final ExampleEntityRepository exampleEntityRepository;

    // Vi bruger constructer injection til at injecte vores repository så vi kan bruge klassen
    public ExampleEntityService(ExampleEntityRepository exampleEntityRepository) {
        this.exampleEntityRepository = exampleEntityRepository;
    }

        /*
        Her kan der indsættes metoder som skal bruges i controlleren
        Du kan lave metoder som gemmer data i databasen
        Du kan lave metoder som henter data fra databasen
        Du kan lave metoder som sletter data fra databasen
        Du kan lave metoder som opdaterer data i databasen
         */


        // Metode som henter alle entities fra databasen
        public List<ExampleEntity> getAllExampleEntities() {
            return exampleEntityRepository.findAll();
        }

        // Metode som henter en entity fra databasen baseret på ID
        public ExampleEntity getExampleEntityById(int id) {
            return exampleEntityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExampleEntity not found"));
        }

        // Metode som gemmer en entity i databasen
        public ExampleEntity saveExampleEntity(ExampleEntity exampleEntity) {
            return exampleEntityRepository.save(exampleEntity);
        }

        // Metode som sletter en entity fra databasen baseret på ID
        public void deleteExampleEntity(int id) {
            exampleEntityRepository.deleteById(id);
        }

        // Metode som opdaterer en entity i databasen
        public ExampleEntity updateExampleEntity(int id, ExampleEntity exampleEntity) {
            ExampleEntity entity = exampleEntityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExampleEntity not found"));
            entity.setName(exampleEntity.getName());
            return exampleEntityRepository.save(entity);
        }



}
