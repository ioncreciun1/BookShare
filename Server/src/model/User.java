package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable
{
  private String userName;
  private String passWord = "";
  private String eMail = "";
  private String firstName = "";
  private String lastName = "";
  private String city = "";
  private String contactInfo = "";
  private int upVotes = 0;

  public User(String userName, String passWord, String eMail, String firstName,
      String lastName, String city, String contactInfo, int upVotes){
    this.userName = userName;
    this.passWord = passWord;
    this.eMail = eMail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.contactInfo = contactInfo;
  }

  public String getUserName()
  {
    return userName;
  }


  public String getPassWord() {
    return passWord;
  }

  public String getEMail() {
    return eMail;
  }

  public String getName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCity() {
    return city;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public int getUpVotes() {
    return upVotes;
  }

  public void setPassWord(String passWord)
  {
    this.passWord = passWord;
  }

  public void seteMail(String eMail)
  {
    this.eMail = eMail;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public void setContactInfo(String contactInfo)
  {
    this.contactInfo = contactInfo;
  }

  public void setUpVotes(int upVotes)
  {
    this.upVotes = upVotes;
  }
}