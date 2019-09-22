package io.github.chicaothiago.cinematicket.bean;

public class AddressBean extends DefaultBean{
    /**
     * No Getter/Setter (SOLID principle)
     * However JSP needs..... -_-
     */
    public Integer id;
    public String street;
    public String number;
    public String zipcode;
    public String city;
    public String state;
    public String country;

    public AddressBean() {
    }

    public AddressBean(
        Integer id,
        String street,
        String number,
        String zipcode,
        String city,
        String state,
        String country
    ) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getFullAddress() {
        return this.street + ", " +
            this.number + ", " +
            this.zipcode + ", " +
            this.city + ", " +
            this.state + ", " +
            this.country;
    }

    public Integer getId() { return id; }

    public String getStreet() { return street; }

    public String getNumber() { return number; }

    public String getZipcode() { return zipcode; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getCountry() { return country; }
}
