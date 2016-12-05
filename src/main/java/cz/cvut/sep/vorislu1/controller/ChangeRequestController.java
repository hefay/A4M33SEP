package cz.cvut.sep.vorislu1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Controller
@RequestMapping("/change-request")
public class ChangeRequestController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "change-request/index";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable long id) {
        return "change-request/show";
    }

    @RequestMapping("/new")
    public String newRequestFor(
            @RequestParam(value = "clientId", required = false) BigInteger clientId) {

        return "change-request/new";
    }

}
