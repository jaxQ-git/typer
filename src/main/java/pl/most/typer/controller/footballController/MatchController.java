package pl.most.typer.controller.footballController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.matches.Match;
import pl.most.typer.service.footballservice.competition.CompetitionService;
import pl.most.typer.service.footballservice.matches.MatchesService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/competitions")
public class MatchController {

    private CompetitionService competitionService;
    private MatchesService matchesService;

    public MatchController(CompetitionService competitionService, MatchesService matchesService) {
        this.competitionService = competitionService;
        this.matchesService = matchesService;
    }

    @GetMapping(value = "/{id}/matches/update")
    public String updateMatchInfo(@PathVariable(value = "id") Integer id) {
        HttpStatus status = matchesService.getMatchInfoFromExternalApi(id);
        if (status.is2xxSuccessful()) {
            return "redirect:/competitions/" + id + "/matches";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/{id}/matches")
    private String getMatches(@PathVariable("id") Integer id, Model model) {
        Optional<Competition> optionalCompetition = competitionService.getCompetition(id);
        Optional<Match> optionalMatch = matchesService.findFirstByCompetition(new Competition(id, null));
        if (optionalCompetition.isEmpty() || optionalMatch.isEmpty()) {
            return "redirect:/competitions/" + id + "/matches/update";
        }
        List<Match> matchByCompetition = matchesService.findAllByCompetition(optionalCompetition.get());

        model.addAttribute("competitionName", competitionService.getCompetitionName(id));
        model.addAttribute("matches", matchByCompetition);
        return "matches";
    }
}
