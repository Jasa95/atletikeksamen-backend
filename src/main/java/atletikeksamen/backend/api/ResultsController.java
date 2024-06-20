package atletikeksamen.backend.api;

import atletikeksamen.backend.dto.ResultDTO;
import atletikeksamen.backend.entity.ResultsEntity;
import atletikeksamen.backend.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {
    @Autowired
    private ResultsService resultService;

    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody ResultDTO resultDTO) {
        ResultDTO createdResult = resultService.createResult(resultDTO);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@PathVariable Integer id, @RequestBody ResultDTO resultDTO) {
        ResultDTO updatedResult = resultService.updateResult(id, resultDTO);
        return new ResponseEntity<>(updatedResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/discipline/{disciplineId}")
    public ResponseEntity<List<ResultDTO>> getResultsByDiscipline(@PathVariable Integer disciplineId) {
        List<ResultDTO> results = resultService.getResultsByDiscipline(disciplineId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
