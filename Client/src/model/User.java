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
  private String phone = "";


  /**
   Class constructor for creates a user using the following parameters
   * @param  userName
   *       the users username used to identify the user
   * @param  passWord
   *       users password for login
   * @param  eMail
   *   users email for registration
   * @param  firstName
   *         users first name
   * @param  lastName
   * @param  city
   * optional @param String phone*/
  public User(String userName, String passWord, String eMail, String firstName,
      String lastName, String city, String phone) {
    this.userName = userName;
    this.passWord = passWord;
    this.eMail = eMail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.phone = phone;
  }

  /**
   * getter that @return String username*/
  public String getUserName()
  {
    return userName;
  }

  /**getter that @return String passWord*/
  public String getPassWord() {
    return passWord;
  }

  /**getter that @return String eMail*/
  public String getEMail() {
    return eMail;
  }

  /**getter that @return String passWord*/
  public String getName() {
    return firstName;
  }

  /**getter that @return String lastname*/
  public String getLastName() {
    return lastName;
  }

  /**getter that @return String lastname*/
  public String getCity() {
    return city;
  }

  /**getter that @return String phone*/
  public String getphone() {
    return phone;
  }

  /**
   * set password
   * @param passWord password of the user
   */
  public void setPassWord(String passWord)
  {
    this.passWord = passWord;
  }

  /**
   * set email of this user
   * @param eMail
   */
  public void setEMail(String eMail)
  {
    this.eMail = eMail;
  }

  /**
   * set First name
   * @param firstName
   * first name of this user
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * set last name
   * @param lastName last name of this user
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * set city
   * @param city city where user lives
   */
  public void setCity(String city)
  {
    this.city = city;
  }

  /**
   * set phone number
   * @param phone phone number of this user
   */
  public void setphone(String phone)
  {
    this.phone = phone;
  }

}