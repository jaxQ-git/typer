package pl.most.typer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.most.typer.model.league.Standing;
import pl.most.typer.service.footballservice.CompetitionService;
import pl.most.typer.service.footballservice.LeagueService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/competitions")
public class CompetitionController {

    private CompetitionService competitionService;
    private LeagueService leagueService;

    public CompetitionController(CompetitionService competitionService, LeagueService leagueService) {
        this.competitionService = competitionService;
        this.leagueService = leagueService;
    }

    @GetMapping(value = "/standings/{id}/update")
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
    private String getStandings(@PathVariable("id") Integer id,
                                @RequestParam(name="group", required = false) String group,
                                Model model) {

        if (!competitionService.existsCompetitionByApiId(id)) {
            return "redirect:/competitions/standings/"+ id + "/update";
        }
        String competitionName = competitionService.getCompetitionName(id);
        List<Standing> standingsByCompetition = competitionService.getStandingsByCompetition(id);
        if (group != null) {
            standingsByCompetition = standingsByCompetition.stream()
                    .filter(standing -> standing.getGroupName().equals(group))
                    .collect(Collectors.toList());
        }

        model.addAttribute("apiId", id);
        model.addAttribute("competitionName", competitionName);
        model.addAttribute("standings", standingsByCompetition);
        return "standings";
    }
}
