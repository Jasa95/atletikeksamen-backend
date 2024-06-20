package template.springbackendtemplate.service;

import org.springframework.stereotype.Service;
import template.springbackendtemplate.repository.ParticipantRepository;

@Service
public class DisciplineService {
    private final ParticipantRepository participantRepository;

    public DisciplineService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }


}
