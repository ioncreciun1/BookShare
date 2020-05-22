package model;

import utility.observer.subject.LocalSubject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model extends LocalSubject<String,Book>
{
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;;
  User getUser(String username) throws RemoteException;
  boolean checkUsername(String username);
  boolean checkPassword(String password);
  boolean checkUser(User user) throws RemoteException;
  boolean checkEmail(User user) throws RemoteException;
  ArrayList<Book> allBooks() throws SQLException,RemoteException;
  void addBook(String title,String author,String description,String language,String category) throws RemoteException, SQLException;
  ArrayList<Book> readByFilter(String filter,String value)
      throws SQLException, RemoteException;
}