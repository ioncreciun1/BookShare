package model;

import java.io.Serializable;

public class BookFly extends AbstractBook implements Serializable
{

  public BookFly(String title, String author)
  {
    super(title, author);
  }
}
