package com.modoodesigner.web.pages;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController implements ErrorController {

    @GetMapping(value = {"/"})
    public String entry() {return "index.html";}


    @GetMapping(value = "/error")
    public String redirectRoot() {
        return "index.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
