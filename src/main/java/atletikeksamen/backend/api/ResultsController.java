package atletikeksamen.backend.api;

import atletikeksamen.backend.dto.ResultDTO;
import atletikeksamen.backend.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    @Autowired
    private ResultsService resultsService;

    @GetMapping
    public List<ResultDTO> getAllResults() {
        return resultsService.getAllResults();
    }

    @PostMapping
    public ResultDTO createResult(@RequestBody ResultDTO resultDTO) {
        return resultsService.createResult(resultDTO);
    }

    @PutMapping("/{id}")
    public ResultDTO updateResult(@PathVariable Integer id, @RequestBody ResultDTO resultDTO) {
        return resultsService.updateResult(id, resultDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Integer id) {
        resultsService.deleteResult(id);
    }

    @GetMapping("/discipline/{disciplineId}")
    public List<ResultDTO> getResultsByDiscipline(@PathVariable Integer disciplineId) {
        return resultsService.getResultsByDiscipline(disciplineId);
    }
}
