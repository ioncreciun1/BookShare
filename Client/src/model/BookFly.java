package model;

import java.io.Serializable;

/**
 * * A class to store the title and author of the book *
 * * @author Johnny Creciun *
 * @version 1.0 – May 2020
 *  implementing AbstractBook
 * */

public class BookFly extends AbstractBook
{
   /*class constructor to store the title and author of the book
   *  @param String title
      the title of the book
      @param String author
      the author of the book
   * */
  public BookFly(String title, String author)
  {
    super(title, author);
  }
}
