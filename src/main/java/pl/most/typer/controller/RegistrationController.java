package pl.most.typer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.most.typer.model.dto.RegistrationForm;
import pl.most.typer.service.CustomUserDetailsService;
import pl.most.typer.service.RegistrationService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/register")
@CrossOrigin("*")
public class RegistrationController {

    RegistrationService registrationService;
    private CustomUserDetailsService customUserDetailsService;


    public RegistrationController(RegistrationService registrationService, CustomUserDetailsService customUserDetailsService) {
        this.registrationService = registrationService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new RegistrationForm());
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
