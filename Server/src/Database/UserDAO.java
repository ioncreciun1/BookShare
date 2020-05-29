package Database;

import model.User;

import java.util.*;
import java.sql.SQLException;


public interface UserDAO
{
  public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo) throws SQLException, Exception;
  public void delete(User user) throws SQLException;
  public User getUser(String username) throws SQLException;
  public List<User> getUsers() throws SQLException;
  public void update(User user) throws SQLException;
  public boolean check_User(User user) throws  SQLException;
  public boolean check_Email(User user) throws SQLException;
  public boolean checkUsername(String username)throws SQLException;

}