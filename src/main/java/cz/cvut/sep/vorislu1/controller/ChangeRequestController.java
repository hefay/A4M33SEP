package cz.cvut.sep.vorislu1.controller;

import cz.cvut.sep.vorislu1.formmodel.ChangeRequestForm;
import cz.cvut.sep.vorislu1.model.*;
import cz.cvut.sep.vorislu1.repository.ChangeRequestRepository;
import cz.cvut.sep.vorislu1.service.ChangeRequestService;
import cz.cvut.sep.vorislu1.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    @Autowired
    ChangeRequestRepository changeRequestRepository;
    @Autowired
    ChangeRequestService changeRequestService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<ChangeRequest> requests = changeRequestService.getAll();
        model.addAttribute("requests", requests);

        return "change-request/index";
    }

    @RequestMapping(value = "/{id}/delete")
    public String delete(@PathVariable long id) {
        changeRequestService.delete(id);
        return "redirect:/change-request";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public String show(@PathVariable long id, Model model) {
        ChangeRequest request = changeRequestService.find(id);

        model.addAttribute("request", request);
        return "change-request/show";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.POST})
    public String update(@PathVariable long id, @ModelAttribute Client chrf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Client oldClient = clientService.find(new BigInteger(String.valueOf(id)));
        if(oldClient == null) {
            return "redirect:/change-request";
        }

        ChangeRequest chr = new ChangeRequest(id, null);

        CompareAndSet<Client> cmpUser = new CompareAndSet<>(chrf, oldClient, chr);
        cmpUser
                .compareAndSet("originCountry")
                .compareAndSet("personalNumber");

        for(int i = 0; i < 5; i++) {
            String fnn = chrf.getFirstNames().size() > i ? chrf.getFirstNames().get(i) : null;
            String fno = oldClient.getFirstNames().size() > i ? oldClient.getFirstNames().get(i) : "";
            String lnn = chrf.getLastNames().size() > i ? chrf.getLastNames().get(i) : null;
            String lno = oldClient.getLastNames().size() > i ? oldClient.getLastNames().get(i) : "";
            if(fnn != null && !fnn.equals(fno)) {
                Change ch = new Change(String.class, "firstNames[" + i + "]", fno, fnn);
                chr.addChange(ch);
            }
            if(lnn != null && !lnn.equals(lno)) {
                Change ch = new Change(String.class, "lastNames[" + i + "]", lno, lnn);
                chr.addChange(ch);
            }
        }

        for(int i = 0; i < 3; i++) {
            Address anew = chrf.getAddresses().size() > i ? chrf.getAddresses().get(i) : null;
            Address aold = oldClient.getAddresses().size() > i ? oldClient.getAddresses().get(i) : new Address();
            if(anew == null) {
                continue;
            }
            CompareAndSet<Address> cmpAddress = new CompareAndSet<>(anew, aold, chr, "addresses[" + i + "].");
            cmpAddress
                    .compareAndSet("streetName")
                    .compareAndSet("streetNumber")
                    .compareAndSet("zipCode")
                    .compareAndSet("city")
                    .compareAndSet("cityPart")
                    .compareAndSet("country")
                    .compareAndSet("region");

            Phone pnew = chrf.getPhones().size() > i ? chrf.getPhones().get(i) : null;
            Phone pold = oldClient.getPhones().size() > i ? oldClient.getPhones().get(i) : new Phone();
            CompareAndSet<Phone> cmpPhone = new CompareAndSet<>(pnew, pold, chr, "phones[" + i + "].");
            cmpPhone
                    .compareAndSet("type")
                    .compareAndSet("phoneNumber")
                    .compareAndSet("cityCode")
                    .compareAndSet("countryCode");
        }


        chr.setType(ChangeRequest.EDIT);
        changeRequestRepository.save(chr);

        return "redirect:/change-request";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String create(@ModelAttribute Client chrf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ChangeRequest chr = new ChangeRequest(null, null);

        for(int i = 0; i < 5; i++) {
            if(!chrf.getFirstNames().get(i).isEmpty()) {
                Change c = new Change(String.class, "firstNames[" + i + "]");
                c.setTo(chrf.getFirstNames().get(i));
                chr.addChange(c);
            }
            if(!chrf.getLastNames().get(i).isEmpty()) {
                Change c = new Change(String.class, "lastNames[" + i + "]");
                c.setTo(chrf.getLastNames().get(i));
                chr.addChange(c);
            }
        }

        for(int i = 0; i < 3; i++) {
            CompareAndSet<Address> cmpAddress = new CompareAndSet<>(chrf.getAddresses().get(i), null, chr, "addresses[" + i + "].");
            CompareAndSet<Phone> cmpPhone = new CompareAndSet<>(chrf.getPhones().get(i), null, chr, "phones[" + i + "].");

            cmpAddress
                    .compareAndSet("streetName")
                    .compareAndSet("streetNumber")
                    .compareAndSet("zipCode")
                    .compareAndSet("city")
                    .compareAndSet("cityPart")
                    .compareAndSet("country")
                    .compareAndSet("region");
            cmpPhone
                    .compareAndSet("type")
                    .compareAndSet("phoneNumber")
                    .compareAndSet("cityCode")
                    .compareAndSet("countryCode");
        }


        chr.setType(ChangeRequest.NEW);
        changeRequestRepository.save(chr);

        return "redirect:/change-request";
    }


    private class CompareAndSet<T> {
        private T a;
        private T b;
        private String prefix;
        private ChangeRequest chr;

        public CompareAndSet(T newO, T origO, ChangeRequest chr) {
            this(newO, origO, chr, null);
        }

        public CompareAndSet(T newO, T origO, ChangeRequest chr, String prefix) {
            this.a = newO;
            this.b = origO;
            this.chr = chr;
            this.prefix = prefix;
        }

        public CompareAndSet<T> compareAndSet(String attribute) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            if(a == null) {
                return this;
            }

            String gmthdn = "get" + Character. toUpperCase(attribute.charAt(0)) + attribute.substring(1);
            Method gmeth = a.getClass().getMethod(gmthdn);
            Object ra = gmeth.invoke(a);
            if(ra == null ) {
                return this;
            }
            if(b == null) {
                if(ra instanceof String && ((String) ra).isEmpty()) {
                    return this;
                }
                String chName = prefix == null ? attribute : prefix + attribute;
                Change ch = new Change(ra.getClass(), chName, null, ra);
                System.out.println("Add change " + ch.toString() + " for " + attribute);
                chr.addChange(ch);
                return this;
            }
            Object rb = gmeth.invoke(b);

            if(!ra.equals(rb)) {
                if(ra instanceof String && ((String) ra).isEmpty() && rb == null) {
                    return this;
                }
                String chName = prefix == null ? attribute : prefix + attribute;
                Change ch = new Change(ra.getClass(), chName, rb, ra);
                System.out.println("Add change " + ch.toString() + " for " + attribute);
                chr.addChange(ch);
            }

            return this;
        }
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
