package model;

public abstract class AbstractBook
{
  private String title;
  private String author;


  public AbstractBook(String title,String author)
  {
    this.title = title;
    this.author = author;


  }

  public String getTitle()
  {
    return title;
  }

  public String getAuthor()
  {
    return author;
  }


}
