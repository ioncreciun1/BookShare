package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model extends LocalSubject<String,Book>
{
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String phone)
      throws Exception;;
  User getUser(String username) throws RemoteException;
   String getUser();
  boolean checkUsername(String username);
  boolean checkPassword(String password);
  boolean checkUser(User user) throws RemoteException;
  boolean checkEmail(User user) throws RemoteException;
  ArrayList<Book> allBooks() throws SQLException,RemoteException;
  void addBook(String title,String author,String description,String language,String category) throws RemoteException, SQLException;
  ArrayList<Book> readByFilter(String filter,String value)
      throws SQLException, RemoteException;
  ArrayList<Book> readByTwoFilters(String filter, String value, String filter1, String value1) throws SQLException,RemoteException;
  ArrayList<Book> readByThreeFilters(String filter, String value, String filter1, String value1,
      String filter2, String value2) throws SQLException,RemoteException;
  ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException,RemoteException;
  ArrayList<Book> booksByUser() throws SQLException,RemoteException;
  void removeBook(Book book) throws SQLException,RemoteException;
  void setAvailable(Book book);
  void setBorrowed(Book book);
  void changeAvailable(Book book, boolean bool) throws SQLException,RemoteException;
  void addComment(Book book, String comment) throws SQLException,RemoteException;
  ArrayList<String> getComments(String bookID) throws SQLException,RemoteException;
}