package Database;

import java.util.HashMap;
import java.util.Map;
/* flyweight is too loose for this and too generic, use Multiton so we can have multiple users access the class
but all using the same underlying map*/
/* Multiton stores a copy of the persisted registrant in the map*/
public class Registrant/*Multiton*/
{
  private static Map<String, Registrant> allInstances = new HashMap<>();
  /* private bc dont want it available globally and static to use it without creating an object of the class*/
  private String userName;
  private String passWord = "";
  private String eMail = "";
  private String firstName = "";
  private String lastName = "";
  private String city = "";
  private String contactInfo = "";
  private int upVotes = 0;

  private Registrant(String userName, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo, int upVotes){
    this.userName = userName;
    this.passWord = passWord;
    this.eMail = eMail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.city = city;
    this.contactInfo = contactInfo;
    this.upVotes = upVotes;
  }



  public static Registrant getInstance(String userName){
    Registrant instance = allInstances.get(userName);
    if(instance == null){
      System.out.println("ERROR - No User Found");
    }
    return instance;
  }

  public synchronized static void addInstance(String userName, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo, int upVotes)
      throws Exception
  {
    Registrant instance = new Registrant(userName,passWord, eMail, firstName, lastName, city, contactInfo, upVotes);
    Registrant usernameExists = allInstances.get(userName);
    if(usernameExists != null)
    {
      throw new Exception("ERROR -Username already used");
      /* System.out.println("ERROR - Username already used");*/
    }

    else{ allInstances.put(userName, instance);}

  }

  public synchronized static void updateInstance(Registrant registrant)
  {
    String regUserName = registrant.getUserName();
    Registrant usernameExists = allInstances.get(regUserName);
    if(usernameExists == null){
      System.out.println("ERROR - Registrant does not exist");
    }
    else{ allInstances.put(regUserName, registrant);}
  }


  public static Map<String, Registrant> getAllInstances()
  {
    return allInstances;
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