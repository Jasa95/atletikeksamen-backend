package template.springbackendtemplate.service;

import org.springframework.stereotype.Service;
import template.springbackendtemplate.repository.DisciplineRepository;

@Service
public class ParticipantService {
    private final DisciplineRepository disciplineRepository;

    public ParticipantService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }
}
