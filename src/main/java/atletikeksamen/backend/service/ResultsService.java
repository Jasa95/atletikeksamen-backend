package atletikeksamen.backend.service;

import atletikeksamen.backend.dto.ResultDTO;
import atletikeksamen.backend.entity.ResultsEntity;
import atletikeksamen.backend.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import atletikeksamen.backend.repository.DisciplineRepository;
import atletikeksamen.backend.repository.ParticipantRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultsService {

    @Autowired
    private final ParticipantRepository participantRepository;
    @Autowired
    private final DisciplineRepository disciplineRepository;
    @Autowired
    private final ResultsRepository resultsRepository;

    public ResultsService(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository, ResultsRepository resultsRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
        this.resultsRepository = resultsRepository;
    }

    public List<ResultDTO> getAllResults() {
        List<ResultsEntity> resultsEntities = resultsRepository.findAll();
        return resultsEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ResultDTO convertToDTO(ResultsEntity resultsEntity) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(resultsEntity.getId());
        resultDTO.setParticipantName(resultsEntity.getParticipant().getName());
        resultDTO.setDisciplineName(resultsEntity.getDiscipline().getName());
        resultDTO.setResultValue(resultsEntity.getResultValue());
        resultDTO.setResultType(resultsEntity.getResultType());
        resultDTO.setDate(resultsEntity.getDate());
        resultDTO.setDisciplineId(resultsEntity.getDiscipline().getId());
        resultDTO.setParticipantId(resultsEntity.getParticipant().getId());
        return resultDTO;
    }

    private ResultsEntity toEntity(ResultDTO dto) {
        ResultsEntity entity = new ResultsEntity();
        entity.setId(dto.getId());
        entity.setDiscipline(disciplineRepository.findById(dto.getDisciplineId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discipline not found")));
        entity.setParticipant(participantRepository.findById(dto.getParticipantId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found")));
        entity.setDate(dto.getDate());
        entity.setResultType(dto.getResultType());
        entity.setResultValue(dto.getResultValue());
        return entity;
    }

    public ResultDTO createResult(ResultDTO resultDTO) {
        ResultsEntity entity = toEntity(resultDTO);
        return toDto(resultsRepository.save(entity));
    }

    public ResultDTO updateResult(Integer id, ResultDTO resultDTO) {
        ResultsEntity existingResult = resultsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found"));
        existingResult.setDate(resultDTO.getDate());
        existingResult.setResultType(resultDTO.getResultType());
        existingResult.setResultValue(resultDTO.getResultValue());
        return toDto(resultsRepository.save(existingResult));
    }

    public void deleteResult(Integer id) {
        resultsRepository.deleteById(id);
    }

    public List<ResultDTO> getResultsByDiscipline(Integer disciplineId) {
        List<ResultsEntity> results = resultsRepository.findByDisciplineId(disciplineId);
        return results.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ResultDTO toDto(ResultsEntity entity) {
        ResultDTO dto = new ResultDTO();
        dto.setId(entity.getId());
        dto.setDisciplineId(entity.getDiscipline().getId());
        dto.setParticipantId(entity.getParticipant().getId());
        dto.setDate(entity.getDate());
        dto.setResultType(entity.getResultType());
        dto.setResultValue(entity.getResultValue());
        return dto;
    }
}
