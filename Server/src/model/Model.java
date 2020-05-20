package model;

import utility.observer.subject.LocalSubject;

import java.sql.SQLException;

public interface Model extends LocalSubject<String,Book>
{
  boolean checkUser(User registrant) throws SQLException;
  public boolean check_Email(User registrant) throws SQLException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
  User getUser(String username) throws SQLException;
  void addBook(Book book) throws SQLException;
}
