package model;

public class User {

    private String login;
    private String password;
    private String name;
    private String email;
    private String phone;//12
    private UserType userType;
    private String accessKey;
    private Address address;

   public User(){}

    public User(String login, String password, String email, String phone, UserType userType) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

    public User(String login, String password, String name, String email, String phone, UserType userType) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

    public void createAddress(String country, String city, String street, String building, String room){
        Address address1 = new Address(country, city, street, building, room);
        address = address1;
    }

    public void createAddress(String city, String street, String building, String room){
        Address address1 = new Address(city, street, building, room);
        address = address1;
    }

    public void createAddress(String street, String building, String room){
        Address address1 = new Address(street, building, room);
        address = address1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (userType != user.userType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", address=" + address.toString() +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}