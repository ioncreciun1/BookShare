package model;

import utility.observer.subject.LocalSubject;

import java.io.IOException;
import java.rmi.RemoteException;

public interface Model extends LocalSubject<String,String>
{
  boolean verifyLog(String password, String name) throws IOException;
  String getUsers() throws RemoteException;
}