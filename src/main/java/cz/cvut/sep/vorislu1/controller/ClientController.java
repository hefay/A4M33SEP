package cz.cvut.sep.vorislu1.controller;

import cz.cvut.sep.service.customer.CustomerDatabaseWSDL;
import cz.cvut.sep.service.customer.CustomerDetailType;
import cz.cvut.sep.service.customer.CustomerType;
import cz.cvut.sep.vorislu1.model.Client;
import cz.cvut.sep.vorislu1.service.ClientService;
import groovy.util.logging.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.xml.ws.Holder;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Client> clients = clientService.findAll(0, 500);
        model.addAttribute("clients", clients);
        return "client/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(Model model, @PathVariable String id) {
        Client client = clientService.find(new BigInteger(id));
        if(client == null) {
            throw new MissingClientException();
        }
        model.addAttribute("client", client);
        return "client/show";
    }

    @ExceptionHandler(MissingClientException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String missingClient() {
        return "client/missing-client";
    }

    class MissingClientException extends RuntimeException {

    }


}
