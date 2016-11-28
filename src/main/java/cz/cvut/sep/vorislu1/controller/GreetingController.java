package cz.cvut.sep.vorislu1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Controller
public class GreetingController {

    @RequestMapping("/")
    public String greeting() {
        return "greeting";
    }
}
