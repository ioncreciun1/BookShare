package model;

import utility.observer.subject.LocalSubject;

import java.io.IOException;
import java.rmi.RemoteException;

public interface Model extends LocalSubject<String,String>
{
  boolean verifyLog(String password, String name) throws IOException;
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
  String getUsers() throws RemoteException;
  public boolean checkUsername(String username);
  public boolean checkPassword(String password);
}