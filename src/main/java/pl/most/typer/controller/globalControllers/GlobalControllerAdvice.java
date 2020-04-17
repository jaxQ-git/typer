package pl.most.typer.controller.globalControllers;

import lombok.Data;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.most.typer.model.dto.HeaderCompetitionListDTO;
import pl.most.typer.model.competition.Competition;

import java.util.*;

@Data
@ControllerAdvice
public class GlobalControllerAdvice {

    public static final String DEFAULT_ERROR_VIEW = "error";
    HeaderCompetitionListDTO headerCompetitionListDTO;

    public GlobalControllerAdvice(HeaderCompetitionListDTO headerCompetitionListDTO) {
        this.headerCompetitionListDTO = headerCompetitionListDTO;
    }



    @ModelAttribute("competitions")
    public List<Competition> getListOfAvailableCompetitions() {
        return headerCompetitionListDTO.getCompetitions();
    }


    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String handleException(Exception ex) {
        return "redirect:/error";
    }


    @GetMapping(value = {"/error"})
    public String ErrorPage() {
        return "error";
    }

}
