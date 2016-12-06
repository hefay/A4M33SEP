package cz.cvut.sep.vorislu1.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public class Client {
    public enum State {
        ACTIVE("Active"), IN_CHANGE("InChange"), SUSPENDED("Suspended"), REFUNDED("Refunded"), DEACTIVED("Deactivated");

        private final String text;

        State(String text) {
            this.text = text;
        }

        public String val() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private BigInteger id;
    private String firstName;
    private List<String> firstNames = new ArrayList<>();
    private String lastName;
    private List<String> lastNames = new ArrayList<>();
    private List<Address> addresses  = new ArrayList<>();
    private List<Phone> phones  = new ArrayList<>();
    private String originCountry;
    private String personalNumber;
    private String state;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(List<String> firstNames) {
        this.firstNames = firstNames;
    }

    public List<String> getLastNames() {
        return lastNames;
    }

    public void setLastNames(List<String> lastNames) {
        this.lastNames = lastNames;
    }
}
