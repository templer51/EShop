package manager;

import model.Address;

public interface AddressManager {

    public void add(String country, String city, String street, int house, int room);

    public void remove(int id);

    public Address get(int id);

    public void change(Address address, int id);

    public boolean isExist(int id);

}