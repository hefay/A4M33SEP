package cz.cvut.sep.vorislu1.mapper;

import cz.cvut.sep.service.customer.AddressType;
import cz.cvut.sep.service.customer.CustomerDetailType;
import cz.cvut.sep.service.customer.PhoneType;
import cz.cvut.sep.vorislu1.model.Address;
import cz.cvut.sep.vorislu1.model.Client;
import cz.cvut.sep.vorislu1.model.Phone;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public class ModelMapper {

    public static Address address(AddressType addressType) {
        if(addressType == null) {
            return null;
        }

        Address address = new Address();
        address.setCity(addressType.getCity());
        address.setCityPart(addressType.getCityPart());
        address.setStreetName(addressType.getStreetName());
        address.setStreetNumber(addressType.getStreetNum());
        address.setCountry(addressType.getCountry());
        address.setRegion(addressType.getCounty());
        address.setZipCode(addressType.getPostalCode());

        return address;
    }

    public static Phone phone(PhoneType phoneType) {
        if(phoneType == null) {
            return null;
        }

        Phone phone = new Phone();
        phone.setCityCode(phoneType.getCityCode());
        phone.setCountryCode(phoneType.getCityCode());
        phone.setPhoneNumber(phoneType.getPhoneNum());
        phone.setType(phoneType(phoneType.getPhoneNumberType()));

        return phone;
    }

    public static Client client(BigInteger id, String status, CustomerDetailType customerDetailType) {
        if(customerDetailType == null) {
            return null;
        }

        Client client = new Client();
        client.setId(id);
        client.setFirstName(customerDetailType.getFirstName().get(0));
        client.setFirstNames(customerDetailType.getFirstName());
        client.setLastName(customerDetailType.getSurname().get(0));
        client.setLastNames(customerDetailType.getSurname());
        client.setOriginCountry(customerDetailType.getCountryOfOrigin());
        client.setPersonalNumber(customerDetailType.getBirthNum());
        List<Address> addresses = new ArrayList<>();
        for(AddressType addressType : customerDetailType.getAddress()) {
            addresses.add(address(addressType));
        }
        List<Phone> phones = new ArrayList<>();
        for(PhoneType phoneType : customerDetailType.getPhoneNum()) {
            phones.add(phone(phoneType));
        }
        client.setAddresses(addresses);
        client.setPhones(phones);
        client.setState(status);

        return client;
    }

    public static Phone.PhoneType phoneType(BigInteger phoneType) {
        if(phoneType == null) {
            return null;
        }
        switch (phoneType.intValue()) {
            case 1:
                return Phone.PhoneType.MOBILE;
            case 2:
                return Phone.PhoneType.FIX;
            default:
                return null;
        }

    }
}
