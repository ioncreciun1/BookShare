package model;

public abstract class AbstractBook
{
  private String title;
  private String author;
  private String language;
  private String book_type;

  public AbstractBook(String title,String author,String language,String book_type)
  {
    this.title = title;
    this.author = author;
    this.language = language;
    this.book_type = book_type;

  }

  public String getTitle()
  {
    return title;
  }

  public String getAuthor()
  {
    return author;
  }

  public String getBook_type()
  {
    return book_type;
  }

  public String getLanguage()
  {
    return language;
  }
}
