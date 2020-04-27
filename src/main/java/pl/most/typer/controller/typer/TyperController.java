package pl.most.typer.controller.typer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.most.typer.exceptions.ResourceException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.service.typer.TyperCompetitionService;
import pl.most.typer.service.typer.TyperStandingService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/typer/competitions")
public class TyperController {

    private final String ERROR_ATTR = "errorMessage";
    private final String ERROR_MSG = "Wystąpił błąd z obsługą: ";

    TyperCompetitionService typerCompetitionService;
    TyperStandingService typerStandingService;


    public TyperController(TyperCompetitionService typerCompetitionService, TyperStandingService typerStandingService) {
        this.typerCompetitionService = typerCompetitionService;
        this.typerStandingService = typerStandingService;
    }

    @GetMapping(value = "/{id}/standings")
    private String getStandings(
            @PathVariable("id") Integer id,
            Model model) {
        TyperCompetition typerCompetition = null;
        try {
            typerCompetition = typerCompetitionService.findById(id);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            model.addAttribute(ERROR_ATTR, ERROR_MSG + "nie znaleziono ligi");
            return "/";
        }

        TyperStanding typerStanding = null;
        try {
            typerStanding = typerStandingService
                    .findLatestStandingByTyperCompetition(typerCompetition);
        } catch (
                ResourceException ex) {
            log.warn(ex.getMessage());
            model.addAttribute(ERROR_ATTR, ERROR_MSG + "nie znaleziono " + ex.getResource());
        }
        model.addAttribute("typerCompetition", typerCompetition);
        model.addAttribute("typerStanding", typerStanding);
        return "typerTemplate/typerStandings";

    }

}
