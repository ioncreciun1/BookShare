package mediator;

import model.Registrant;
import org.postgresql.core.SqlCommand;
import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RemoteModel extends RemoteSubject<String,String>
{
  boolean verifyPass(String password, String username) throws RemoteException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
  void getRegistrant(String username) throws RemoteException, SQLException;
  boolean checkUser(Registrant registrant) throws RemoteException, SQLException;
  boolean checkEmail(Registrant registrant) throws RemoteException,SQLException;
}
