package manager;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import model.Address;

public class AddressManagerImpl implements AddressManager{

    private AddressDAO dao = new AddressDAOImpl();

    @Override
    public void add(String country, String city, String street, int house, int room) {
        Address address = new Address(country, city, street, house, room);
        dao.add(address);
    }

    @Override
    public void remove(int id) {
        dao.remove(id);
    }

    @Override
    public Address get(int id) {
        return dao.get(id);
    }

    @Override
    public void change(Address address, int id) {
        dao.update(address, id);
    }

    @Override
    public boolean isExist(int id) {
        return dao.find(id);
    }
}