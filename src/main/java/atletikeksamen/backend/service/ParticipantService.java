package atletikeksamen.backend.service;

import atletikeksamen.backend.dto.DisciplineDTO;
import atletikeksamen.backend.dto.ParticipantDTO;
import atletikeksamen.backend.entity.DisciplineEntity;
import atletikeksamen.backend.entity.ParticipantEntity;
import atletikeksamen.backend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import atletikeksamen.backend.repository.DisciplineRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private final ParticipantRepository participantRepository;
    @Autowired
    private final DisciplineRepository disciplineRepository;

    public ParticipantService(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ParticipantDTO getParticipantById(Integer id) {
        return participantRepository.findById(id).map(this::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found"));
    }

    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        ParticipantEntity participant = toEntity(participantDTO);
        ParticipantEntity savedParticipant = participantRepository.save(participant);
        return toDTO(savedParticipant);
    }

    public ParticipantDTO updateParticipant(Integer id, ParticipantDTO participantDTO) {
        ParticipantEntity participant = participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found"));
        participant.setName(participantDTO.getName());
        participant.setAge(participantDTO.getAge());
        participant.setGender(participantDTO.getGender());
        participant.setClub(participantDTO.getClub());

        List<DisciplineEntity> disciplines = participantDTO.getDisciplineNames().stream()
                .map(disciplineName -> disciplineRepository.findByName(disciplineName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discipline not found")))
                .collect(Collectors.toList());
        participant.setDisciplines(disciplines);

        ParticipantEntity updatedParticipant = participantRepository.save(participant);
        return toDTO(updatedParticipant);
    }

    private ParticipantDTO toDTO(ParticipantEntity participantEntity) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participantEntity.getId());
        participantDTO.setName(participantEntity.getName());
        participantDTO.setAge(participantEntity.getAge());
        participantDTO.setGender(participantEntity.getGender());
        participantDTO.setClub(participantEntity.getClub());

        List<String> disciplineNames = participantEntity.getDisciplines().stream()
                .map(DisciplineEntity::getName)
                .collect(Collectors.toList());
        participantDTO.setDisciplineNames(disciplineNames);

        return participantDTO;
    }

    private ParticipantEntity toEntity(ParticipantDTO participantDTO) {
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setId(participantDTO.getId());
        participantEntity.setName(participantDTO.getName());
        participantEntity.setAge(participantDTO.getAge());
        participantEntity.setGender(participantDTO.getGender());
        participantEntity.setClub(participantDTO.getClub());

        List<DisciplineEntity> disciplines = participantDTO.getDisciplineNames().stream()
                .map(disciplineName -> disciplineRepository.findByName(disciplineName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discipline not found")))
                .collect(Collectors.toList());
        participantEntity.setDisciplines(disciplines);

        return participantEntity;
    }

    public void deleteParticipant(Integer id) {
        if (!participantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found");
        }
        participantRepository.deleteById(id);
    }
}

