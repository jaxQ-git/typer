package pl.most.typer.controller.globalControllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class DefaultErrorController implements ErrorController {


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
