package cz.cvut.sep.vorislu1.service;

import cz.cvut.sep.service.customer.*;
import cz.cvut.sep.vorislu1.model.Change;
import cz.cvut.sep.vorislu1.model.ChangeRequest;
import cz.cvut.sep.vorislu1.model.Client;
import cz.cvut.sep.vorislu1.repository.ChangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
@Service
public class ChangeRequestServiceImpl implements ChangeRequestService {
    @Autowired
    ChangeRequestRepository changeRequestRepository;
    @Autowired
    CustomerDatabaseWSDL customerDatabase;
    @Autowired
    ClientService clientService;

    @Override
    public List<ChangeRequest> getAll() {
        Iterable<ChangeRequest> chrr = changeRequestRepository.findAll();
        List<ChangeRequest> chr = new ArrayList<>();
        for(ChangeRequest ch : chrr) {
            chr.add(ch);
        }

        return chr;
    }

    @Override
    public ChangeRequest find(long id) {
        return changeRequestRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        ChangeRequest chr = changeRequestRepository.findOne(id);
        changeRequestRepository.delete(chr);
    }

    @Override
    public void pushAll() {
        List<ChangeRequest> requests = changeRequestRepository.findBySync(false);
        for(ChangeRequest reqest : requests) {
            push(reqest);
        }
    }

    @Override
    public void push(ChangeRequest changeRequest) {
        CreateCustomerChangeOrder changeOrder = new CreateCustomerChangeOrder();
        changeOrder.setId(new BigInteger(String.valueOf(changeRequest.getClientId())));
        changeOrder.setRequestType(changeOrder.getRequestType());
        changeOrder.setCustomer(createCustomerFromChange(changeRequest));
        customerDatabase.createCustomerChangeOrder(changeOrder);

        changeRequest.setSync();
        changeRequestRepository.save(changeRequest);
    }

    private CustomerDetailType createCustomerFromChange(ChangeRequest cr) {
        CustomerDetailType cdt;

        Holder<BigInteger> id = new Holder<>(new BigInteger(String.valueOf(cr.getClientId())));
        Holder<String> status = new Holder<>(new String());
        Holder<CustomerDetailType> detail = new Holder<>(new CustomerDetailType());
        customerDatabase.readCustomerDetails(id, status, detail);
        cdt = detail.value;

        List<String> firstNames = cdt.getFirstName();
        List<String> lastNames = cdt.getSurname();
        List<AddressType> addresses = cdt.getAddress();
        List<PhoneType> phones = cdt.getPhoneNum();

        for(Change c : cr.getChanges()) {
            for(int i = 0; i < 3; i++) {
                setAddress(addresses, c, i);
                setPhone(phones, c, i);
                setFirstName(firstNames, c, i);
                setLastName(lastNames, c, i);
            }
            for(int i = 3; i < 5; i++) {
                setFirstName(firstNames, c, i);
                setLastName(lastNames, c, i);
            }
            if(c.getName() == "originCountry") {
                cdt.setCountryOfOrigin((String) c.getTo());
            }
            if(c.getName() == "personalNumber") {
                cdt.setBirthNum((String) c.getTo());
            }
        }

        return cdt;
    }

    private void setAddress(List<AddressType> addresses, Change c, int ad) {
        String startWith = "addresses[" + ad + "].";


        if(c.getName().startsWith(startWith)) {
            if(addresses.size() <= ad) {
                while(addresses.size() <= ad) {
                    addresses.add(new AddressType());
                }
            }
            AddressType address = addresses.get(ad);

            String paramName = c.getName().substring(startWith.length());
            System.out.println(paramName);
            switch (paramName) {
                case "streetName":
                    address.setStreetName((String) c.getTo());
                    break;
                case "streetNumber":
                    address.setStreetNum((String) c.getTo());
                    break;
                case "zipCode":
                    address.setPostalCode((String) c.getTo());
                    break;
                case "city":
                    address.setCity((String) c.getTo());
                    break;
                case "cityPart":
                    address.setCityPart((String) c.getTo());
                    break;
                case "country":
                    address.setCountry((String) c.getTo());
                    break;
                case "region":
                    address.setCounty((String) c.getTo());
                    break;
            }
        }
    }
    private void setPhone(List<PhoneType> phones, Change c, int ad) {
        String startWith = "phones[" + ad + "].";


        if(c.getName().startsWith(startWith)) {
            if(phones.size() <= ad) {
                while(phones.size() <= ad) {
                    phones.add(new PhoneType());
                }
            }
            PhoneType phone = phones.get(ad);

            String paramName = c.getName().substring(startWith.length());
            System.out.println(paramName);
            switch (paramName) {
                case "phoneNumber":
                    phone.setPhoneNum((String) c.getTo());
                    break;
                case "cityCode":
                    phone.setCityCode((String) c.getTo());
                    break;
                case "countryCode":
                    phone.setCountryCode((String) c.getTo());
                    break;
                case "type":
                    phone.setPhoneNumberType(new BigInteger((String) c.getTo()));
                    break;
            }
        }
    }
    private void setFirstName(List<String> names, Change c, int ad) {
        String startWith = "firstNames[" + ad + "].";

        if(c.getName().startsWith(startWith)) {
            if(names.size() <= ad) {
                while(names.size() <= ad) {
                    names.add(null);
                }
            }
            names.set(ad, (String) c.getTo());
        }
    }
    private void setLastName(List<String> names, Change c, int ad) {
        String startWith = "lastNames[" + ad + "].";

        if(c.getName().startsWith(startWith)) {
            if(names.size() <= ad) {
                while(names.size() <= ad) {
                    names.add(null);
                }
            }
            names.set(ad, (String) c.getTo());
        }
    }
}
