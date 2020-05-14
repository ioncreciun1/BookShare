package model;

import utility.observer.subject.LocalSubject;

import java.sql.SQLException;

public interface Model extends LocalSubject<String,String>
{
  boolean checkUser(Registrant registrant) throws SQLException;
  public boolean check_Email(Registrant registrant) throws SQLException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
  Registrant getRegistrant(String username) throws SQLException;
}
