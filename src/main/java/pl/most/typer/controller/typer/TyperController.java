package pl.most.typer.controller.typer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Standing;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperLeagueStanding;
import pl.most.typer.service.typer.TyperCompetitionService;
import pl.most.typer.service.typer.TyperLeagueStandingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping (value = "/typer")
public class TyperController {

    TyperLeagueStandingService typerLeagueStandingService;
    TyperCompetitionService typerCompetitionService;

    public TyperController(TyperLeagueStandingService typerLeagueStandingService,
                           TyperCompetitionService typerCompetitionService) {
        this.typerLeagueStandingService = typerLeagueStandingService;
        this.typerCompetitionService = typerCompetitionService;
    }

    @GetMapping(value = "/{id}/standings")
    private String getStandings(@PathVariable("id") Integer id,
                                Model model) {
        Optional<TyperCompetition> optionalTyperCompetition = typerCompetitionService.findById(id);
        List<TyperLeagueStanding> typerLeagueStandings = typerLeagueStandingService.findByTyperCompetitionId(id);
        //Add attributes to model
        model.addAttribute("typerCompetition", optionalTyperCompetition.orElseGet(() -> {
            TyperCompetition typerCompetition = new TyperCompetition();
            typerCompetition.setName("myname");
            return typerCompetition;
        }));
        model.addAttribute("typerLStandings", typerLeagueStandings);

        return "typerStandings";
    }
}
