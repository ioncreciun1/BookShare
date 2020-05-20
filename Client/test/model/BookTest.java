package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest
{
  private Book book;

  @BeforeEach void setUp()
  {
    System.out.println("----> setUp()");

  }

  @AfterEach void tearDown()
  {
    System.out.println("---> tearDown()");
  }

  @Test void getUsername()
  {
    this.book = new Book("sam12345","Harrp Potter","JK ROwling", "English", "idk","fiction");
    assertEquals("sam12345", book.getUsername());
  }

}