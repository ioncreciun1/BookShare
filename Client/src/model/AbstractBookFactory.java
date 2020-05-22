package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for book that store title and author
 */
public class AbstractBookFactory
{
  private static Map<String,AbstractBook> bookMap = new HashMap<>();

  /**
   * Getting an Abstract book by  title and synchronize it so it will be thread safe
   * @param title title of abstract book
   * @param author author of abstract book
   * @return an AbstractBook
   */
  public static AbstractBook getBookFly(String title,String author)
  {
    AbstractBook book = bookMap.get(title);
    if(book == null) /*lazy instantiation*/
    {
      synchronized (bookMap)/*thread safe*/
      {
        book = bookMap.get(title);
        if (book == null)
        {
          book = new BookFly(title, author);/*creates an instance of a book and puts it into the hashmap*/
          bookMap.put(title, book);
        }
      }
    }
    return  book;
  }
}
