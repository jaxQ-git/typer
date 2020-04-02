package pl.most.typer.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.most.typer.data.UserRepository;
import pl.most.typer.service.RegistrationService;

import java.util.UUID;

@Controller
@RequestMapping("/register")
@CrossOrigin("*")
public class RegistrationController {

    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String register(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){
        registrationService.register(registrationForm);
        return "redirect:/login";
    }

    @GetMapping(value="/confirm")
    public String confirmRegistration(@RequestParam(value = "key") UUID key) {
        registrationService.confirmUser(key.toString());
        return "redirect:/login";
    }
}
