package model;

import Database.RegistrantDAO;
import Database.RegistrantDAOImplementation;
import utility.observer.listener.GeneralListener;

import java.sql.SQLException;

public class ModelManager implements Model
{
  RegistrantDAO registrant;
  public ModelManager() throws SQLException
  {
    this.registrant = new RegistrantDAOImplementation();
  }

  @Override public boolean checkUser(Registrant registrant) throws SQLException
  {
    return this.registrant.check_User(registrant);
  }

  @Override public boolean check_Email(Registrant registrant)
      throws SQLException
  {
    return this.registrant.check_Email(registrant);
  }

  public void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception
  {
    System.out.println("SERVER MODEL MODEL");
    registrant.add(Username,passWord,eMail,firstName,lastName,city,contactInfo,0);
  }

  @Override public Registrant getRegistrant(String username) throws SQLException
  {
    registrant.getRegistrant(username);
    return null;
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
