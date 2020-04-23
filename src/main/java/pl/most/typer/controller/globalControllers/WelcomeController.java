package pl.most.typer.controller.globalControllers;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping
    public String welcome(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
