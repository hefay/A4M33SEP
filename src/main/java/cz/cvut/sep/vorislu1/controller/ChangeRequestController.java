package cz.cvut.sep.vorislu1.controller;

import cz.cvut.sep.vorislu1.formmodel.ChangeRequestForm;
import cz.cvut.sep.vorislu1.model.Address;
import cz.cvut.sep.vorislu1.model.Client;
import cz.cvut.sep.vorislu1.model.Phone;
import cz.cvut.sep.vorislu1.model.User;
import cz.cvut.sep.vorislu1.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Controller
@RequestMapping("/change-request")
public class ChangeRequestController {
    @Autowired
    ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "change-request/index";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable long id) {
        return "change-request/show";
    }

    @RequestMapping("/aaa")
    public String update(@ModelAttribute Client chrf) {
        System.out.println("aaaa");
        System.out.println("Country of origin: " + chrf.getOriginCountry());
        System.out.println("Country of origin: " + chrf.getPhones().get(1).getCityCode());
        return "redirect:/change-request";
    }

    @RequestMapping("/new")
    public String newRequestFor(
            Model model,
            @RequestParam(value = "clientId", required = false) BigInteger clientId) {

        Client client = new Client();
        if(clientId != null) {
            client = clientService.find(clientId);
        }
        List<String> firstNames = client.getFirstNames();
        while(firstNames.size() < 5) {
            firstNames.add("");
        }
        List<String> lastNames = client.getLastNames();
        while(lastNames.size() < 5) {
            lastNames.add("");
        }
        Phone emptyPhone = new Phone();
        List<Phone> phones = client.getPhones();
        while(phones.size() < 3) {
            phones.add(emptyPhone);
        }
        Address emptyAddress = new Address();
        List<Address> addresses = client.getAddresses();
        while(addresses.size() < 3) {
            addresses.add(emptyAddress);
        }

        model.addAttribute("client", client);

        return "change-request/new";
    }

}
