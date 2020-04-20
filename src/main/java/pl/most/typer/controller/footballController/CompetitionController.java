package pl.most.typer.controller.footballController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Standing;
import pl.most.typer.service.footballservice.competition.CompetitionService;
import pl.most.typer.service.footballservice.competition.LeagueService;
import pl.most.typer.service.footballservice.competition.StandingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/competitions")
public class CompetitionController {

    private CompetitionService competitionService;
    private LeagueService leagueService;
    private StandingService standingService;


    public CompetitionController(CompetitionService competitionService, LeagueService leagueService, StandingService standingService) {
        this.competitionService = competitionService;
        this.leagueService = leagueService;
        this.standingService = standingService;

    }

    @GetMapping(value = "/{id}/standings/update")
    public String updateStandingsInfo(@PathVariable(value = "id") Integer id){
        HttpStatus status  = leagueService.getStandingInfoFromExternalApi(id);
        if (status.is2xxSuccessful()) {
            return "redirect:/competitions/"+ id + "/standings";
        }
        else {
            return "redirect:/";
        }

    }

    @GetMapping(value = "/{id}/standings")
    private String getStandings(@PathVariable("id") Integer id,
                                @RequestParam(name="group", required = false) String group,
                                Model model) {
        Optional<Competition> optionalCompetition = competitionService.getCompetition(id);
        Optional<Standing> optionalStanding = standingService.findFirstByCompetiton(new Competition(id, null));
        //If competition doesn't exists try to update it from external api
        if (optionalCompetition.isEmpty() || optionalStanding.isEmpty()) {
            return "redirect:/competitions/"+ id + "/standings/update";
        }
        List<Standing> standingsByCompetition = standingService.getStandingsByCompetition(optionalCompetition.get());
        //If group parameter exists filter standings by group name
        if (group != null) {
            standingsByCompetition = standingsByCompetition.stream()
                    .filter(standing -> standing.getGroupName().equals(group))
                    .collect(Collectors.toList());
        }

        //Add attributes to model
        model.addAttribute("apiId", id);
        model.addAttribute("competitionName", competitionService.getCompetitionName(id));
        model.addAttribute("standings", standingsByCompetition);
        return "standings";
    }
}
