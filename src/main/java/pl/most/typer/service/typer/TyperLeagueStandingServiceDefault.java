package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import pl.most.typer.model.competition.LeagueStanding;
import pl.most.typer.model.typer.TyperLeagueStanding;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.repository.typerrepo.TyperLeagueStandingRepository;
import pl.most.typer.repository.typerrepo.TyperStandingRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TyperLeagueStandingServiceDefault implements TyperLeagueStandingService {

    TyperLeagueStandingRepository typerLeagueStandingRepository;
    TyperStandingService typerStandingService;

    public TyperLeagueStandingServiceDefault(
            TyperLeagueStandingRepository typerLeagueStandingRepository,
            TyperStandingService typerStandingService) {
        this.typerLeagueStandingRepository = typerLeagueStandingRepository;
        this.typerStandingService = typerStandingService;
    }

    @Override
    public List<TyperLeagueStanding> getLeagueStandings() {
        return typerLeagueStandingRepository.findAll();
    }

    @Override
    public List<TyperLeagueStanding> findByTyperCompetitionId(Integer id) {
        List<TyperStanding> typerStandings = typerStandingService.findAllByCompetitionId(id);
        List<TyperLeagueStanding> result = typerStandings.stream()
                .map(typerStanding -> typerLeagueStandingRepository.findAllByTyperStanding(typerStanding))
                .flatMap(typerLeagueStandings -> typerLeagueStandings.stream())
                .collect(Collectors.toList());
        return result;
    }
}
