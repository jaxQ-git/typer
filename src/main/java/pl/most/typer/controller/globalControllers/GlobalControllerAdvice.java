package pl.most.typer.controller.globalControllers;

import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.most.typer.model.dto.HeaderCompetitionListDTO;
import pl.most.typer.model.competition.Competition;

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
