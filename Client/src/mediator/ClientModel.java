package mediator;

import utility.observer.subject.LocalSubject;
import java.rmi.RemoteException;

public interface ClientModel extends LocalSubject<String,String>
{
  boolean verifyPass(String password,String username) throws RemoteException;
}
