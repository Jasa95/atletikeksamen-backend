package atletikeksamen.backend;

import atletikeksamen.backend.entity.DisciplineEntity;
import atletikeksamen.backend.entity.ParticipantEntity;
import atletikeksamen.backend.entity.ResultsEntity;
import atletikeksamen.backend.repository.DisciplineRepository;
import atletikeksamen.backend.repository.ParticipantRepository;
import atletikeksamen.backend.repository.ResultsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringBackendTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackendTemplateApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(DisciplineRepository disciplineRepository, ParticipantRepository participantRepository, ResultsRepository resultsRepository) {
        return args -> {
            // Disciplines
            DisciplineEntity discipline1 = new DisciplineEntity();
            discipline1.setName("100m");
            discipline1.setResultsType("Time");
            disciplineRepository.save(discipline1);

            DisciplineEntity discipline2 = new DisciplineEntity();
            discipline2.setName("200m");
            discipline2.setResultsType("Time");
            disciplineRepository.save(discipline2);

            // Participants
            ParticipantEntity participant1 = new ParticipantEntity();
            participant1.setName("John Doe");
            participant1.setAge(25);
            participant1.setGender("mand");
            participant1.setClub("Aarhus 1900");
            participantRepository.save(participant1);

            ParticipantEntity participant2 = new ParticipantEntity();
            participant2.setName("Jane Doe");
            participant2.setAge(25);
            participant2.setGender("kvinde");
            participant2.setClub("Aarhus 1900");
            participantRepository.save(participant2);

            // Results
            ResultsEntity results1 = new ResultsEntity();
            results1.setResultType("Time");
            results1.setDate(new Date());
            results1.setResultValue("10.5");
            results1.setDiscipline(discipline1);
            results1.setParticipant(participant1);
            resultsRepository.save(results1);

            ResultsEntity results2 = new ResultsEntity();
            results2.setResultType("Time");
            results2.setDate(new Date());
            results2.setResultValue("21.5");
            results2.setDiscipline(discipline2);
            results2.setParticipant(participant2);
            resultsRepository.save(results2);
        };
    }
}
