package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    public void add(User user);

    public void remove(String login);

    public boolean find(String login);

    public User get(String login);

    public void update(User user);

    public List<User> getAll();
}