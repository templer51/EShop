package dao;

import model.Address;

public interface AddressDAO {

    public void add(Address address);

    public void remove(int id);

    public Address get(int id);

    public Address get(String name);

    public boolean find(int id);

    public boolean find(String name);

    public void update(Address address, int id);

}
