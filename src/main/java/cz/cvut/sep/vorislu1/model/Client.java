package cz.cvut.sep.vorislu1.model;

import java.math.BigInteger;

/**
 * @author Vorisek Lukas <lukasvorisek@gmail.com>
 */
public class Client {
    private BigInteger id;
    private String firstName;
    private String lastName;

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
}
