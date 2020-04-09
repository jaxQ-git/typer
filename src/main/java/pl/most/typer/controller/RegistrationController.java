package pl.most.typer.controller;

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
import pl.most.typer.service.CustomUserDetailsService;
import pl.most.typer.service.LeagueService;
import pl.most.typer.service.RegistrationService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/register")
@CrossOrigin("*")
public class RegistrationController {

    private RegistrationService registrationService;
    private CustomUserDetailsService customUserDetailsService;
    private LeagueService leagueService;


    public RegistrationController(RegistrationService registrationService, CustomUserDetailsService customUserDetailsService, LeagueService leagueService) {
        this.registrationService = registrationService;
        this.customUserDetailsService = customUserDetailsService;
        this.leagueService = leagueService;
    }

    @GetMapping
    public String register(Model model) throws JSONException {
        model.addAttribute("user", new RegistrationForm());
//        leagueService.getStandingInfoFromExternalApi(2001);
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
