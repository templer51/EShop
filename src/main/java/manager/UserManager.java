package manager;

import model.User;

import java.util.List;

public interface UserManager {

    public void register(String login, String pass, String name, String phone, String email);

    public void change(int id, String login, String pass, String name, String phone, String email);

    public User get(String login);

    public List<User> getAll();

    public void delete(String login);

    public boolean isExist(String login);

    public String signIn(String login, String pass);

    public void logout(String accessKey);

}