package model;

import java.io.Serializable;

public class User implements Serializable
{
  private String userName;
  private String passWord = "";
  private String eMail = "";
  private String firstName = "";
  private String lastName = "";
  private String city = "";
  private String contactInfo = "";


/*Class constructor for creates a user using the following parameters
* @param String userName
*       the users username used to identify the user
* @param String passWord
*       users password for login
* @param String eMail
*   users email for registration
* @param String firstName
*         users first name
* @param String lastName
* @param String city
* optional @param String contactInfo*/
  public User(String userName, String passWord, String eMail, String firstName,
      String lastName, String city, String contactInfo) {
    this.userName = userName;
    this.passWord = passWord;
    this.eMail = eMail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.contactInfo = contactInfo;
  }

  /*getter that @return String username*/
  public String getUserName()
  {
    return userName;
  }

  /*getter that @return String passWord*/
  public String getPassWord() {
    return passWord;
  }

  /*getter that @return String eMail*/
  public String getEMail() {
    return eMail;
  }

  /*getter that @return String passWord*/
  public String getName() {
    return firstName;
  }

  /*getter that @return String lastname*/
  public String getLastName() {
    return lastName;
  }

  /*getter that @return String lastname*/
  public String getCity() {
    return city;
  }

  /*getter that @return String contactInfo*/
  public String getContactInfo() {
    return contactInfo;
  }


  public void setPassWord(String passWord)
  {
    this.passWord = passWord;
  }

  public void setEMail(String eMail)
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

}