package pl.most.typer.service;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;
import pl.most.typer.repository.CompetitionRepository;

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

    public List<Standing> getStandingsByCompetition(Integer competitionId) {
        return getStandingsByCompetition(competitionId,"TOTAL");
    }

    public List<Standing> getStandingsByCompetition(Integer competitionId, String standingsType) {
        Optional<Competition> optionalCompetition = findByApiId(competitionId);
        return getStandingsByType(optionalCompetition, Optional.of(standingsType));
    }


    /**
     *
     * @param optionalCompetition
     * @param standingsType - If null the method will return Total League standings
     * @return
     */
    private List<Standing> getStandingsByType(Optional<Competition> optionalCompetition, Optional<String> standingsType) {
        return optionalCompetition.get().getStandings()
                .stream()
                .filter(standing -> standingsType.orElse("TOTAL").equals(standing.getType()))
                .collect(Collectors.toList());
    }
}
