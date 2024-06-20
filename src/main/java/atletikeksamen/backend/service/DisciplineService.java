package atletikeksamen.backend.service;

import atletikeksamen.backend.dto.DisciplineDTO;
import atletikeksamen.backend.dto.ParticipantDTO;
import atletikeksamen.backend.entity.DisciplineEntity;
import atletikeksamen.backend.entity.ParticipantEntity;
import atletikeksamen.backend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import atletikeksamen.backend.repository.ParticipantRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplineService {
    @Autowired
    private final ParticipantRepository participantRepository;
    @Autowired
    private final DisciplineRepository disciplineRepository;

    public DisciplineService(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public DisciplineDTO getDisciplineById(Integer id) {
        return disciplineRepository.findById(id).map(this::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discipline not found"));
    }

    public DisciplineDTO createDiscipline(DisciplineDTO disciplineDTO) {
        DisciplineEntity discipline = toEntity(disciplineDTO);
        DisciplineEntity savedDiscipline = disciplineRepository.save(discipline);
        return toDTO(savedDiscipline);
    }

    public DisciplineDTO updateDiscipline(Integer id, DisciplineDTO disciplineDTO) {
        DisciplineEntity discipline = disciplineRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discipline not found"));
        discipline.setName(disciplineDTO.getName());
        discipline.setResultsType(disciplineDTO.getResultsType());

        DisciplineEntity updatedDiscipline = disciplineRepository.save(discipline);
        return toDTO(updatedDiscipline);
    }

    private DisciplineDTO toDTO(DisciplineEntity disciplineEntity) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(disciplineEntity.getId());
        disciplineDTO.setName(disciplineEntity.getName());
        disciplineDTO.setResultsType(disciplineEntity.getResultsType());

        List<String> participantNames = disciplineEntity.getParticipants().stream()
                .map(ParticipantEntity::getName)
                .collect(Collectors.toList());
        disciplineDTO.setParticipantNames(participantNames);

        return disciplineDTO;
    }

    private DisciplineEntity toEntity(DisciplineDTO disciplineDTO) {
        DisciplineEntity disciplineEntity = new DisciplineEntity();
        disciplineEntity.setId(disciplineDTO.getId());
        disciplineEntity.setName(disciplineDTO.getName());
        disciplineEntity.setResultsType(disciplineDTO.getResultsType());
        return disciplineEntity;
    }
    public void deleteDiscipline(Integer id) {
        DisciplineEntity discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discipline not found"));

        // Remove the discipline from all participants
        for (ParticipantEntity participant : discipline.getParticipants()) {
            participant.getDisciplines().remove(discipline);
            participantRepository.save(participant);
        }

        disciplineRepository.deleteById(id);
    }

}
