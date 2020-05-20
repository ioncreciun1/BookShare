package mediator;

import model.Book;
import model.User;
import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RemoteModel extends RemoteSubject<String,Book>
{
  boolean verifyPass(String password, String username) throws RemoteException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
  User getUser(String username) throws RemoteException, SQLException;
  boolean checkUser(User registrant) throws RemoteException, SQLException;
  boolean checkEmail(User registrant) throws RemoteException,SQLException;
  void addBook(Book book) throws RemoteException,SQLException;
}
