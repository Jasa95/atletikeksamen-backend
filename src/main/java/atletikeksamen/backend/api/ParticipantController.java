package atletikeksamen.backend.api;

import atletikeksamen.backend.dto.ParticipantDTO;
import atletikeksamen.backend.entity.ParticipantEntity;
import atletikeksamen.backend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public List<ParticipantDTO> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ParticipantDTO getParticipantById(@PathVariable Integer id) {
        return participantService.getParticipantById(id);
    }

    @PostMapping
    public ParticipantDTO createParticipant(@RequestBody ParticipantDTO participantDTO) {
        return participantService.createParticipant(participantDTO);
    }

    @PutMapping("/{id}")
    public ParticipantDTO updateParticipant(@PathVariable Integer id, @RequestBody ParticipantDTO participantDTO) {
        return participantService.updateParticipant(id, participantDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Integer id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

}
