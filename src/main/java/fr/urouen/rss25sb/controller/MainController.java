package fr.urouen.rss25sb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the RSS Service!";
    }

    @GetMapping("/help")
    public String help() {
        return "This is the help page for the RSS Service API.\n" +
                "Available endpoints:\n" +
                "- GET / : Welcome message\n" +
                "- GET /help : This help page\n" +
                "- GET /articles : List all articles (to be implemented)\n" +
                "- GET /articles/{guid} : Get article by GUID (to be implemented)";
    }
}
