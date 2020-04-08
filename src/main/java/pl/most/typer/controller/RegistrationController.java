package pl.most.typer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.most.typer.model.dto.RegistrationForm;
import pl.most.typer.model.league.CompetitionDTO;
import pl.most.typer.model.league.Standing;
import pl.most.typer.repository.StandingRepository;
import pl.most.typer.service.CustomUserDetailsService;
import pl.most.typer.service.RegistrationService;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.UUID;

@Controller
@RequestMapping("/register")
@CrossOrigin("*")
public class RegistrationController {

    private RegistrationService registrationService;
    private CustomUserDetailsService customUserDetailsService;
    private StandingRepository standingRepository;


    public RegistrationController(RegistrationService registrationService, CustomUserDetailsService customUserDetailsService, StandingRepository standingRepository) {
        this.registrationService = registrationService;
        this.customUserDetailsService = customUserDetailsService;
        this.standingRepository = standingRepository;
    }

    @GetMapping
    public String register(Model model) throws JSONException {
        model.addAttribute("user", new RegistrationForm());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Auth-Token","d6d4a40948f344e78fd1b8a461c4d213");
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(httpHeaders);
        String url = "https://api.football-data.org/v2/competitions/2001/standings";
        ResponseEntity<CompetitionDTO> entity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                stringHttpEntity,
                CompetitionDTO.class
        );
        CompetitionDTO competitionDTO = entity.getBody();
        competitionDTO.getStandings()
                .stream()
                .forEach(standing -> standing.getTable().forEach(leagueStanding -> leagueStanding.setStanding(standing)));


        Standing standing = competitionDTO.getStandings().get(0).getTable().get(0).getStanding();
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("user") RegistrationForm registrationForm,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        if (customUserDetailsService.isUserPresent(registrationForm.getUsername(), registrationForm.getMail())) {
            model.addAttribute("exist", true);
            return "/registration";
        }
        registrationService.register(registrationForm);
        return "/login";
    }

    @GetMapping(value = "/confirm")
    public String confirmRegistration(@RequestParam(value = "key") UUID key) {
        registrationService.confirmUser(key.toString());
        return "login";
    }
}
