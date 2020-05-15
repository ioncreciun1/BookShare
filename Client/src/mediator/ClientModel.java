package mediator;

import model.Registrant;
import utility.observer.subject.LocalSubject;
import java.rmi.RemoteException;

public interface ClientModel extends LocalSubject<String,String>
{
  boolean verifyPass(String password,String username) throws RemoteException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
  void getRegistrant(String username) throws RemoteException;
  boolean checkUser(Registrant registrant) throws RemoteException;
  boolean checkEmail(Registrant registrant) throws RemoteException;
}
