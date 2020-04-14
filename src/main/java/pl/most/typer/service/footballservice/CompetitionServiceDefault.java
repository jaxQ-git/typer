package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;
import pl.most.typer.repository.footballrepo.CompetitionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        return competitionRepository.save(competition);
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
