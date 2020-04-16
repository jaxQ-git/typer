package pl.most.typer.controller.accountController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.most.typer.repository.accountrepo.UserRepository;
import pl.most.typer.service.accountservice.CustomUserDetailsService;

@RequestMapping("/rest/hello")
@RestController
public class UserController {

    private CustomUserDetailsService customUserDetailsService;
    private UserRepository userRepository;

    public UserController(CustomUserDetailsService customUserDetailsService, UserRepository userRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }
}
