package employees;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String zip;

    private String city;

    private String line;

    public Address() {
    }

    public Address(String zip, String city, String line) {
        this.zip = zip;
        this.city = city;
        this.line = line;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
