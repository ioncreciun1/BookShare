package mediator;

import model.Book;
import model.User;
import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface RemoteModel extends RemoteSubject<String,Book>
{
  boolean verifyPass(String password,String username) throws RemoteException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String phone)
      throws Exception;
  User getUser(String username) throws RemoteException;
  boolean checkUser(User user) throws RemoteException;
  boolean checkEmail(User user) throws RemoteException;
  void addBook(Book book) throws RemoteException, SQLException;;
  ArrayList<Book> allBooks() throws SQLException,RemoteException;
  ArrayList<Book> readByFilter(String filter,String value) throws SQLException,RemoteException;
  ArrayList<Book> readByTwoFilters(String filter, String value, String filter1, String value1) throws SQLException,RemoteException;
  ArrayList<Book> readByThreeFilters(String filter, String value, String filter1, String value1,
      String filter2, String value2) throws SQLException,RemoteException;
  ArrayList<Book> readByAllFilters(String title, String author,
      String language, String category) throws SQLException,RemoteException;
  ArrayList<Book> booksByUser(String username) throws SQLException,RemoteException;
  void removeBook(Book book) throws SQLException,RemoteException;
  void changeAvailable(Book book, boolean bool) throws SQLException,RemoteException;
  void addComment(String BookID, String Username, String comment) throws SQLException,RemoteException;
  boolean checkUsername(String username) throws RemoteException, SQLException;
 ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException;
  void update(String actualUsername, String newUsername, String passWord, String eMail, String firstName, String lastName, String city, String phone) throws SQLException,RemoteException;
}