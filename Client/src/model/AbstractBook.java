package model;

import java.io.Serializable;

/**
 * An abstract book that stores title and author
 */
public abstract class AbstractBook implements Serializable
{
  private String title;
  private String author;

  /**
   * Two parameter constructor that initiate the title and author
   * @param title title of Abstract Book
   * @param author author of Abstract book
   */
  public AbstractBook(String title,String author)
  {
    this.title = title;
    this.author = author;
  }

  /**
   * getter for title
   * @return title of abstractBook
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * Getter for author
   * @return author of abstract book
   */
  public String getAuthor()
  {
    return author;
  }
}
