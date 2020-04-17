package pl.most.typer.controller.globalControllers;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequestMapping("/")

public class WelcomeController {



    @GetMapping
    public String welcome(){
        return "index";
    }
}
