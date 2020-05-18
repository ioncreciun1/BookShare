package model;

import java.io.Serializable;

public class BookFly extends AbstractBook implements Serializable
{

  public BookFly(String title, String author, String language, String book_type)
  {
    super(title, author, language, book_type);
  }
}
