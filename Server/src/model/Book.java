package model;

import java.io.Serializable;

/**
 * implements serializable
 * *Serialization in Java is a mechanism of writing the state of an object into a byte-stream for RMI.
 * .*/
public class Book  implements Serializable
{
  private String description;
  private String username;
  private AbstractBook book;
  private String language;
  private String category;
  private State state;
  private String bookID;
  /**
   class constructor to create a book from input from user
   @param  username
   the users identification
    * @param  title
  the title of the book
   @param author
   the author of the book
   @param  language
   the language that is book is written selected by dropdown by user
   @param  description
   the description of the book entered by the user,
   @param  category
   the category of the book selected by the user
   */
  public Book(String username, String title, String author, String language, String description, String category){
    this.state = new BookStateOn();
    this.description = description;
    this.language = language;
    this.category = category;
    book = AbstractBookFactory.getBookFly(title,author);
    this.username = username;
  }

  /**
   * Seven parameter Constructor.
   * @param username
   * owner of this book
   * @param bookID
   * unique identifier for this book
   * @param title
   * title of the book
   * @param author
   * author of this book
   * @param language
   * language of this book
   * @param description
   * description of the book
   * @param category
   * category of the book
   */
  public Book(String username, String bookID, String title, String author, String language, String description, String category){
    this.state = new BookStateOn();
    this.description = description;
    this.language = language;
    this.category = category;
    this.bookID = bookID;
    book = AbstractBookFactory.getBookFly(title,author);
    this.username = username;
  }
  /**
   @return  username
    *      returns the username of the of Owner of this book*/
  public String getUsername(){
    return username;
  }
  /**
   * @return  title
   *      returns the title of the book*/
  public String getTitle(){
    return book.getTitle();
  }
  /**
   * @return  author
   *      returns the author of the book*/
  public String getAuthor(){
    return book.getAuthor();
  }
  /**
   * @return language
   *      returns the language of the book*/
  public String getLanguage(){
    return language;
  }
  /**
   * @return category
   *      returns the category of the book*/
  public String getCategory(){
    return category;
  }
  /**
   * @return title
   *      returns the title of the book*/
  public String getDescription(){
    return description;
  }

  /**
   * Set book status to available
   */
  public void setAvailable()
  {
    state.setAvailable(this);
  }
  /**
   * Set book status to borrowed
   */
  public void setBorrowed()
  {
    state.setBorrowed(this);
  }

  /**
   * set new State of this book
   * @param newState new state of this book
   */
  public void setState(State newState) {
    state = newState;
  }
  /**
   @return boolean available
    *   returns a state of available or borrowed*/
  public boolean available(){
    return state.available();
  }

  /**
   * Get bookID
   * @return bookID
   */
  public String getBookID()
  {
    return bookID;
  }
}
