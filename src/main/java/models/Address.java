package models;

public class Address {
    private String line1;
    private String city;
    private String postal_code;
    private String state;
    private String country;

    // Default constructor added for deserializing
    public Address()
    {

    }

    public Address(String line1, String city, String postal_code, String state, String country)
    {
        this.line1=line1;
        this.city=city;
        this.postal_code=postal_code;
        this.state=state;
        this.country=country;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
