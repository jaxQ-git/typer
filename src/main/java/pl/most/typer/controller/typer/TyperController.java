package pl.most.typer.controller.typer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.service.typer.TyperCompetitionService;
import pl.most.typer.service.typer.TyperStandingService;

import java.util.Optional;

@Controller
@RequestMapping (value = "/typer")
public class TyperController {

    TyperCompetitionService typerCompetitionService;
    TyperStandingService typerStandingService;

    public TyperController(TyperCompetitionService typerCompetitionService, TyperStandingService typerStandingService) {
        this.typerCompetitionService = typerCompetitionService;
        this.typerStandingService = typerStandingService;
    }

    @GetMapping(value = "/{id}/standings")
    private String getStandings(@PathVariable("id") Integer id,
                                Model model) {
        Optional<TyperCompetition> optionalTyperCompetition = typerCompetitionService.findById(id);
        //Add attributes to model
        if ((optionalTyperCompetition.isPresent())) {
            Optional<TyperStanding> typerStanding = typerStandingService
                    .findLatestStandingByTyperCompetition(optionalTyperCompetition.get());
            model.addAttribute("typerCompetition", optionalTyperCompetition.get());
            model.addAttribute("typerStanding", typerStanding.get());
            return "typerStandings";
        }


        return "redirect: /";
    }
}
