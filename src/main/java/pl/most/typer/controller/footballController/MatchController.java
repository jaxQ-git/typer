package pl.most.typer.controller.footballController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.matches.Match;
import pl.most.typer.service.footballservice.competition.CompetitionService;
import pl.most.typer.service.footballservice.matches.MatchesService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/competitions")
public class MatchController {

    private CompetitionService competitionService;
    private MatchesService matchesService;

    public MatchController(CompetitionService competitionService, MatchesService matchesService) {
        this.competitionService = competitionService;
        this.matchesService = matchesService;
    }

    @GetMapping(value = "/{id}/stages/update")
    public String updateMatchInfo(@PathVariable(value = "id") Integer id) {
        Optional<Match> optionalMatch = getFirstMatch(id);
        if (optionalMatch.isPresent()) {
            return "redirect:/competitions/" + id + "/stages";
        }
        HttpStatus status = matchesService.getMatchInfoFromExternalApi(id);
        if (status.is2xxSuccessful()) {
            return "redirect:/competitions/" + id + "/stages";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/{id}/stages")
    private String getStages(@PathVariable("id") Integer id,
                             @RequestParam(name = "stage", required = false) String stage,
                             Model model) {
        Optional<Competition> optionalCompetition = getCompetition(id);
        Optional<Match> optionalMatch = getFirstMatch(id);
        if (checkIfCompetitionMatchExist(optionalCompetition, optionalMatch)) {
            return "redirect:/competitions/" + id + "/stages/update";
        }

        List<Match> matchByCompetition = getAllMatches(optionalCompetition);
        matchByCompetition = filterMatchesByStage(stage, matchByCompetition);

        LinkedHashMap<String, List<Match>> matchesByStage = getStageLinkedHashMap(matchByCompetition);

        model.addAttribute("apiId", id);
        model.addAttribute("competitionName", competitionService.getCompetitionName(id));
        model.addAttribute("matchesByStage", matchesByStage);
        model.addAttribute("matches", matchByCompetition);
        return "stages";
    }

    @GetMapping(value = "/{id}/matches")
    private String getMatchesByStages(@PathVariable("id") Integer id,
                                      @RequestParam(name = "stage", required = false) String stage,
                                      Model model) {
        Optional<Competition> optionalCompetition = getCompetition(id);
        List<Match> matchByCompetition = getAllMatches(optionalCompetition);

        matchByCompetition = filterMatchesByStage(stage, matchByCompetition);
        LinkedHashMap<String, List<Match>> matchesByGroup = getGroupLinkedHashMap(matchByCompetition);

        model.addAttribute("competitionName", competitionService.getCompetitionName(id));
        model.addAttribute("matchesByGroup", matchesByGroup);
        model.addAttribute("stageName", stage);
        return "matches";
    }

    private LinkedHashMap<String, List<Match>> getStageLinkedHashMap(List<Match> matchByCompetition) {
        return matchByCompetition
                .stream()
                .collect(Collectors.groupingBy(Match::getStage, LinkedHashMap::new, Collectors.toList()));
    }

    private LinkedHashMap<String, List<Match>> getGroupLinkedHashMap(List<Match> matchByCompetition) {
        return matchByCompetition
                .stream()
                .collect(Collectors.groupingBy(match -> match.getGroup() == null ? "absentGroup" : match.getGroup(), LinkedHashMap::new, Collectors.toList()));
    }

    private List<Match> filterMatchesByStage(@RequestParam(name = "stage", required = false) String stage, List<Match> matchByCompetition) {
        if (stage != null) {
            matchByCompetition = matchByCompetition.stream()
                    .filter(match -> match.getStage().equals(stage))
                    .collect(Collectors.toList());
        }
        return matchByCompetition;
    }

    private List<Match> filterMatchesByGroup(String group, List<Match> matchByCompetition) {
        if (group != null) {
            matchByCompetition = matchByCompetition.stream()
                    .filter(match -> match.getGroup().equals(group))
                    .collect(Collectors.toList());
        }
        return matchByCompetition;
    }

    private Optional<Match> getFirstMatch(@PathVariable("id") Integer id) {
        return matchesService.findFirstByCompetition(new Competition(id, null));
    }

    private boolean checkIfCompetitionMatchExist(Optional<Competition> optionalCompetition, Optional<Match> optionalMatch) {
        if (optionalCompetition.isEmpty() || optionalMatch.isEmpty()) {
            return true;
        }
        return false;
    }

    private Optional<Competition> getCompetition(@PathVariable("id") Integer id) {
        return competitionService.getCompetition(id);
    }

    private List<Match> getAllMatches(Optional<Competition> optionalCompetition) {
        return matchesService.findAllByCompetition(optionalCompetition.get());
    }

}
