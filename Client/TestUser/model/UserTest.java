package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  private User user;

  @BeforeEach void setUp()
  {
    System.out.println("----> setUp()");
    this.user = new User("TheLordy","123456","builder@gmail.com",
        "Bob","TheBuilder","Horsens","72663512");
  }

  @AfterEach void tearDown()
  {
    System.out.println("----> tearDown()");
  }

  @Test void getUserName()
  {
    assertEquals("TheLordy",user.getUserName());
  }

  @Test void getPassWord()
  {
    assertEquals("123456",user.getPassWord());
  }

  @Test void getEMail()
  {
    assertEquals("builder@gmail.com",user.getEMail());
  }

  @Test void getName()
  {
    assertEquals("Bob",user.getName());
  }

  @Test void getLastName()
  {
    assertEquals("TheBuilder",user.getLastName());
  }

  @Test void getCity()
  {
    assertEquals("Horsens",user.getCity());
  }

  @Test void getContactInfo()
  {
    assertEquals("72663512",user.getContactInfo());
  }

  @Test void setPassWord()
  {
    user.setPassWord("654321");
    assertEquals("654321",user.getPassWord());
  }

  @Test void setMail()
  {
    user.setMail("lord@gmail.com");
    assertEquals("lord@gmail.com",user.getEMail());
  }

  @Test void setFirstName()
  {
    user.setFirstName("George");
    assertEquals("George",user.getName());
  }

  @Test void setLastName()
  {
    user.setLastName("TheHouse");
    assertEquals("TheHouse",user.getLastName());
  }

  @Test void setCity()
  {
    user.setCity("Aarhus");
    assertEquals("Aarhus",user.getCity());
  }

  @Test void setContactInfo()
  {
    user.setContactInfo("12345678");
    assertEquals("12345678",user.getContactInfo());
  }
}