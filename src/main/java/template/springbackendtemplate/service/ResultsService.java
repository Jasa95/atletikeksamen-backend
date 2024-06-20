package template.springbackendtemplate.service;

import org.springframework.stereotype.Service;
import template.springbackendtemplate.repository.DisciplineRepository;
import template.springbackendtemplate.repository.ParticipantRepository;

@Service
public class ResultsService {
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    public ResultsService(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }
}
