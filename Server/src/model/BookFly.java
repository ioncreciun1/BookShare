package model;

import java.io.Serializable;

/**
 * * A class to store the title and author of the book
 *  implementing AbstractBook
 * */

public class BookFly extends AbstractBook
{
  /** class constructor to store the title and author of the book
    @param  title
     the title of the book
     @param  author
     the author of the book
   */
  public BookFly(String title, String author)
  {
    super(title, author);
  }
}
