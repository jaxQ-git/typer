package pl.most.typer.controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.most.typer.model.account.RegistrationForm;
import pl.most.typer.model.matches.Match;
import pl.most.typer.service.accountservice.CustomUserDetailsService;
import pl.most.typer.service.footballservice.LeagueService;
import pl.most.typer.service.accountservice.RegistrationService;
import pl.most.typer.service.footballservice.MatchesService;
import pl.most.typer.service.footballservice.MatchesServiceDefault;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/register")
@CrossOrigin("*")
public class RegistrationController {

    private RegistrationService registrationService;
    private CustomUserDetailsService customUserDetailsService;
    private MatchesService matchesService;




    public RegistrationController(RegistrationService registrationService, CustomUserDetailsService customUserDetailsService, LeagueService leagueService, MatchesService matchesService) {
        this.registrationService = registrationService;
        this.customUserDetailsService = customUserDetailsService;
        this.matchesService = matchesService;

    }

    @GetMapping
    public String register(Model model) throws JSONException {
        model.addAttribute("user", new RegistrationForm());
        matchesService.getMatchesByCompetitionId(2001);
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
