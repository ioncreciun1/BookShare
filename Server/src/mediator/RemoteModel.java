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
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String phone)
      throws Exception;
  User getUser(String username) throws RemoteException, SQLException;
  boolean checkUser(User user) throws RemoteException, SQLException;
  boolean checkUsername(String username) throws RemoteException, SQLException;
  boolean checkEmail(User user) throws RemoteException, SQLException;
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
  void addComment(String BookID, String Username, String comment) throws RemoteException,SQLException;
  ArrayList<String> getComments(String BookID)
          throws SQLException, RemoteException;
}