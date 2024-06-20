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

import java.util.ArrayList;
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
        return participantRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ParticipantDTO getParticipantById(Integer id) {
        return participantRepository.findById(id).map(this::toDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found"));
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
        return toDto(updatedParticipant);
    }

    private ParticipantEntity toEntity(ParticipantDTO dto) {
        ParticipantEntity entity = new ParticipantEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setClub(dto.getClub());

        // Check if disciplineNames is null and handle the Optional<DisciplineEntity>
        if (dto.getDisciplineNames() != null) {
            List<DisciplineEntity> disciplines = dto.getDisciplineNames().stream()
                    .map(disciplineName -> disciplineRepository.findByName(disciplineName))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            entity.setDisciplines(disciplines);
        } else {
            entity.setDisciplines(new ArrayList<>());
        }

        return entity;
    }

    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        ParticipantEntity entity = toEntity(participantDTO);
        return toDto(participantRepository.save(entity));
    }

    private ParticipantDTO toDto(ParticipantEntity entity) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setClub(entity.getClub());
        dto.setDisciplineNames(entity.getDisciplines().stream().map(DisciplineEntity::getName).collect(Collectors.toList()));
        return dto;
    }

    public void deleteParticipant(Integer id) {
        if (!participantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found");
        }
        participantRepository.deleteById(id);
    }
}
