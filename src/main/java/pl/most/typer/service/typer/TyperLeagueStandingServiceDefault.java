package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperLeagueStanding;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.repository.typerrepo.TyperLeagueStandingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TyperLeagueStandingServiceDefault implements TyperLeagueStandingService {

    private TyperLeagueStandingRepository typerLeagueStandingRepository;
    private TyperStandingService typerStandingService;

    public TyperLeagueStandingServiceDefault(
            TyperLeagueStandingRepository typerLeagueStandingRepository,
            TyperStandingService typerStandingService) {
        this.typerLeagueStandingRepository = typerLeagueStandingRepository;
        this.typerStandingService = typerStandingService;
    }



}
