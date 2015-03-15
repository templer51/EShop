package manager;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;
import model.UserType;

import java.util.List;

public class UserManagerImpl implements UserManager{

    private UserDAO dao = new UserDAOImpl();

    @Override
    public void register(String login, String pass, String name, String phone, String email) {
        User user = new User(login, pass, name, phone, email, UserType.Customer);
        dao.add(user);
    }

    @Override
    public void change(int id, String login, String pass, String name, String phone, String email) {
        User user = new User(login, pass, name, phone, email, UserType.Customer);
        dao.update(user);
    }

    @Override
    public User get(String login) {
        return dao.get(login);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void delete(String login) {
        dao.remove(login);
    }

    @Override
    public boolean isExist(String login) {
        return dao.find(login);
    }

    @Override
    public String signIn(String login, String pass) {
        return "";
    }

    @Override
    public void logout(String accessKey) {

    }
}