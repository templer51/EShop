package model;


public class Address {


    int id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String room;

    public Address() {}

    public Address(String country, String city, String street, String building, String room) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.room = room;
    }

    public Address(String city, String street, String building, String room) {
        country = "Ukraine";
        this.city = city;
        this.street = street;
        this.building = building;
        this.room = room;
    }

    public Address(String street, String building, String room) {
        country = "Ukraine";
        city = "Kuiv";
        this.street = street;
        this.building = building;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", room=" + room +
                '}';
    }
}