package model;

import mediator.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{
  private ModelManager model;

  @BeforeEach void setUp()
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = new ModelManager();
    System.out.println("----> setUp()");
  }

  @AfterEach void tearDown()
  {
    System.out.println("----> tearDown()");
  }

  @Test void checkUsername()
  {
    assertEquals(false, model.checkUsername("TheLordny"));
    assertEquals(false, model.checkUsername("TheLordy"));
    assertEquals(true, model.checkUsername("TheLo"));
  }

  @Test void checkPassword()
  {
    assertEquals(false, model.checkPassword("123456"));
    assertEquals(false, model.checkPassword("12345678"));
    assertEquals(true, model.checkPassword("1234"));
  }
}