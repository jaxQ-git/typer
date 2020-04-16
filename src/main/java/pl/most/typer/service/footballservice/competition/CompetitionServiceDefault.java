package pl.most.typer.service.footballservice.competition;

import org.springframework.stereotype.Service;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.repository.footballrepo.CompetitionRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CompetitionServiceDefault implements CompetitionService {

    private CompetitionRepository competitionRepository;

    public CompetitionServiceDefault(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Optional<Competition> findByApiId(Integer apiId) {
        return competitionRepository.findByApiId(apiId);
    }

    @Override
    public boolean existsCompetitionByApiId(Integer apiId) {
        return competitionRepository.existsCompetitionByApiId(apiId);
    }

    @Override
    public Competition save(Competition competition) {
        Optional<Competition> optionalCompetition = competitionRepository.findByApiId(competition.getApiId());
        //Jeżeli liga w bazie danych istnieje to ją aktualizuję i zwracam ligę zapisaną w bazie danych
        if (optionalCompetition.isPresent()) {
            Competition competitionDB = optionalCompetition.get();
            if(!competitionDB.getLastUpdated().isEqual(competition.getLastUpdated())){
                competitionDB.setPlan(competition.getPlan());
                competitionDB.setCode(competition.getCode());
                competitionDB.setName(competitionDB.getPlan());
                competitionDB.setLastUpdated(competition.getLastUpdated());
                competitionRepository.save(competitionDB);
            }
            return competitionDB;
        }
        // W innym przypadku zapisuje ligę w bazie i ją zwracam
        else {
            return competitionRepository.save(competition);
        }
    }




    @Override
    public Optional<Competition> getCompetition(Integer id) {
        return competitionRepository.findByApiId(id);
    }

    @Override
    public String getCompetitionName(Integer id) {
        Optional<Competition> competition = getCompetition(id);
        if (competition.isPresent()) {
            return competition.get().getName();
        }
        return null;
    }

    @Override
    public List<Competition> getAll() {
        return (List<Competition>) competitionRepository.findAll();
    }


}
