package mediator;

import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String,String>
{
  boolean verifyPass(String password, String username) throws RemoteException;
}
