package cz.cvut.sep.vorislu1.model;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public class Phone {
    public enum PhoneType {MOBILE, FIX}
    private PhoneType type;
    private String phoneNumber;
    private String cityCode;
    private String countryCode;

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
