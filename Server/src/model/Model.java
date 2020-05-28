package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Model extends LocalSubject<String,Book>
{
  boolean checkUser(User registrant) throws SQLException;
  boolean checkUsername(String username) throws SQLException;
  public boolean check_Email(User registrant) throws SQLException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String phone)
      throws Exception;
  User getUser(String username) throws SQLException;
  void addBook(Book book) throws SQLException;
  ArrayList<Book> allBooks() throws SQLException;
  ArrayList<Book> readByFilter(String filter,String value) throws SQLException;
   ArrayList<Book> readByTwoFilters(String filter, String value, String filter1, String value1) throws SQLException;
  ArrayList<Book> readByThreeFilters(String filter, String value, String filter1, String value1,
      String filter2, String value2) throws SQLException;
  ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException;
  ArrayList<Book> booksByUser(String username) throws SQLException,
      RemoteException;
  void removeBook(Book book) throws SQLException;
  void changeAvailable(Book book, boolean bool) throws SQLException,RemoteException;
  void add(String BookID, String Username, String comment) throws RemoteException,SQLException;

  ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException;
}
