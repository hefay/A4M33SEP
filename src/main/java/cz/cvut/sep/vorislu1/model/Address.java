package cz.cvut.sep.vorislu1.model;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public class Address {
    private String streetName;
    private String streetNumber;
    private String zipCode;
    private String city;
    private String cityPart;
    private String country;
    private String region;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityPart() {
        return cityPart;
    }

    public void setCityPart(String cityPart) {
        this.cityPart = cityPart;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
