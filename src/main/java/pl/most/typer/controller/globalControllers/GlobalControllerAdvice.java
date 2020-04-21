package pl.most.typer.controller.globalControllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.most.typer.model.dto.HeaderCompetitionListDTO;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.repository.typerrepo.TyperCompetitionRepository;

import java.util.*;

@Slf4j
@Data
@ControllerAdvice
public class GlobalControllerAdvice {

    public static final String DEFAULT_ERROR_VIEW = "error";
    private HeaderCompetitionListDTO headerCompetitionListDTO;
    private TyperCompetitionRepository typerCompetitionRepository;

    public GlobalControllerAdvice(
            HeaderCompetitionListDTO headerCompetitionListDTO,
            TyperCompetitionRepository typerCompetitionRepository) {
        this.headerCompetitionListDTO = headerCompetitionListDTO;
        this.typerCompetitionRepository = typerCompetitionRepository;
    }

    @ModelAttribute("competitions")
    public List<Competition> getListOfAvailableCompetitions() {
        return headerCompetitionListDTO.getCompetitions();
    }
    @ModelAttribute("typerCompetitions")
    public List<TyperCompetition> getTypersCompetitions() {
        return typerCompetitionRepository.findAll();
    }


    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String handleException(Exception ex, Model model) {
        log.error(ex.getMessage(),ex);
        return "redirect:/error";
    }


    @GetMapping(value = {"/error"})
    public String ErrorPage() {
        return "error";
    }

}
