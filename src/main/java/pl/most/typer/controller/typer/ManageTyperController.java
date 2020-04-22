package pl.most.typer.controller.typer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.dto.TyperCompetitionDTO;
import pl.most.typer.service.typer.TyperCompetitionService;
import pl.most.typer.service.typer.TyperStandingService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping (value = "/typer/manager")
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
        return "typer/manageTyper";
    }

    @PostMapping
    private String addTyperCompetition(@Valid @ModelAttribute("typerCompetitionDTO") TyperCompetitionDTO typerCompetitionDTO,
                       BindingResult bindingResult) {
        if (typerCompetitionDTO != null && !StringUtils.isEmpty(typerCompetitionDTO.getName())) {
            try {
                typerCompetitionService.save(typerCompetitionDTO.toTyperCompetition());
            } catch (ResourceAlreadyExistsException ex) {
                bindingResult.rejectValue(ex.getIssue(),"error."+ex.getIssue(),ex.getMessage());
            }
            if (bindingResult.hasErrors()) {
                return "typer/manageTyper";
            }
            return "redirect:/typer/manager";
        }
        return "typer/manageTyper";
    }

    @GetMapping(value = "/{id}/delete")
    private String deleteTyperCompetition(@PathVariable("id") Integer id)  {
        try {
            typerCompetitionService.deleteById(id);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            //TODO przekazac informacje o bledzie do modelu??
        }
        return "redirect:/typer/manager";
    }


}
