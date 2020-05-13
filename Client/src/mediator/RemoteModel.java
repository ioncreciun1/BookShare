package mediator;

import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,String>
{
  boolean verifyPass(String password,String username) throws RemoteException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
}
