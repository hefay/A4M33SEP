package cz.cvut.sep.vorislu1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "client/index";
    }

}
