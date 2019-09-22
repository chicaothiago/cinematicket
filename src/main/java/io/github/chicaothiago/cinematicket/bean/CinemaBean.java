package io.github.chicaothiago.cinematicket.bean;

public class CinemaBean extends DefaultBean {
    /**
     * No Getter/Setter (SOLID principle)
     */
    public Integer id;
    public String name;
    public AddressBean address;

    public CinemaBean() {
        this.name = "";
        this.address = new AddressBean();
    }

    public CinemaBean(
        Integer id,
        String name,
        AddressBean address
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return id;
    }

    public AddressBean getAddress() {
        return address;
    }
}