package pl.most.typer.controller.typer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.model.typer.dto.TyperCompetitionDTO;
import pl.most.typer.service.typer.TyperCompetitionService;
import pl.most.typer.service.typer.TyperStandingService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping (value = "/typer/manage")
public class ManageTyperController {

    TyperCompetitionService typerCompetitionService;
    TyperStandingService typerStandingService;

    private final int ROW_PER_PAGE = 5;

    public ManageTyperController(TyperCompetitionService typerCompetitionService, TyperStandingService typerStandingService) {
        this.typerCompetitionService = typerCompetitionService;
        this.typerStandingService = typerStandingService;
    }

    @ModelAttribute("typerCompetitionDTO")
    public TyperCompetitionDTO getTyperCompetitionDTO(){ return new TyperCompetitionDTO();}

    @ModelAttribute
    public void getAll(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<TyperCompetition> typerCompetitions = typerCompetitionService.findAll(pageNumber, ROW_PER_PAGE);
        long count = typerCompetitionService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("typerCompetitions", typerCompetitions);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        model.addAttribute("typerCompetitionDTO", new TyperCompetitionDTO());
    }



    @GetMapping
    private String manageTyper()  {
        return "manageTyper";
    }

    @PostMapping
    private String add(@ModelAttribute("typerCompetitionDTO") TyperCompetitionDTO typerCompetitionDTO) {
        if (typerCompetitionDTO != null && typerCompetitionDTO.getName()!= null) {
            TyperCompetition typerCompetition = new TyperCompetition(typerCompetitionDTO.getName());
            typerCompetitionService.save(typerCompetition);
            return "redirect:/typer/manage";
        }
        return "/typer/manage";
    }


}
