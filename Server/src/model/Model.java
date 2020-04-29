package model;

import utility.observer.subject.LocalSubject;

public interface Model extends LocalSubject<String,String>
{
  void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception;
}
