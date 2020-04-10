package pl.most.typer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.most.typer.model.league.Standing;
import pl.most.typer.service.CompetitionService;
import pl.most.typer.service.LeagueService;

import java.util.List;

@Controller
@RequestMapping(value = "/competitions")
public class CompetitionController {

    private CompetitionService competitionService;
    private LeagueService leagueService;

    public CompetitionController(CompetitionService competitionService, LeagueService leagueService) {
        this.competitionService = competitionService;
        this.leagueService = leagueService;
    }

    @GetMapping(value = "/update/{id}")
    public String updateStandingsInfo(@PathVariable(value = "id") Integer id){
        HttpStatus status  = leagueService.getStandingInfoFromExternalApi(id);
        if (status.is2xxSuccessful()) {
            return "redirect:/competitions/standings/"+ id;
        }
        else {
            return "redirect:/";
        }

    }

    @GetMapping(value = "/{id}/standings")
    private String getStandings(@PathVariable("id") Integer id, Model model) {
        List<Standing> standingsByCompetition = competitionService.getStandingsByCompetition(id);
        model.addAttribute("standings", standingsByCompetition);
        return "standings";
    }
}
