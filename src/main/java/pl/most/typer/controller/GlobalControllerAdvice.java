package pl.most.typer.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.most.typer.model.dto.HeaderCompetitionListDTO;
import pl.most.typer.model.league.Competition;
import pl.most.typer.service.footballservice.FootballApiService;

import java.util.*;

@Data
@ControllerAdvice
public class GlobalControllerAdvice {

    HeaderCompetitionListDTO headerCompetitionListDTO;

    public GlobalControllerAdvice(HeaderCompetitionListDTO headerCompetitionListDTO) {
        this.headerCompetitionListDTO = headerCompetitionListDTO;
    }



    @ModelAttribute("competitions")
    public List<Competition> getListOfAvailableCompetitions() {
        return headerCompetitionListDTO.getCompetitions();
    }


}
