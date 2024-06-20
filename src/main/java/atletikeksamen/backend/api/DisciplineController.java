package atletikeksamen.backend.api;

import atletikeksamen.backend.dto.DisciplineDTO;
import atletikeksamen.backend.entity.DisciplineEntity;
import atletikeksamen.backend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public DisciplineDTO getDisciplineById(@PathVariable Integer id) {
        return disciplineService.getDisciplineById(id);
    }

    @PostMapping
    public DisciplineDTO createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        return disciplineService.createDiscipline(disciplineDTO);
    }

    @PutMapping("/{id}")
    public DisciplineDTO updateDiscipline(@PathVariable Integer id, @RequestBody DisciplineDTO disciplineDTO) {
        return disciplineService.updateDiscipline(id, disciplineDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Integer id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }

}
