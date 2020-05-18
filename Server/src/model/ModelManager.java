package model;

import Database.UserDAO;
import Database.UserDAOImplementation;
import utility.observer.listener.GeneralListener;

import java.sql.SQLException;

public class ModelManager implements Model
{
  UserDAO user;
  public ModelManager() throws SQLException
  {
    this.user = new UserDAOImplementation();
  }

  @Override public boolean checkUser(User user) throws SQLException
  {
    return this.user.check_User(user);
  }

  @Override public boolean check_Email(User user)
      throws SQLException
  {
    return this.user.check_Email(user);
  }

  public void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception
  {
    System.out.println("SERVER MODEL MODEL");
    user.add(Username,passWord,eMail,firstName,lastName,city,contactInfo,0);
  }

  @Override public User getUser(String username) throws SQLException
  {

    return user.getUser(username);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return false;
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return false;
  }
}
