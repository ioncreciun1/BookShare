package model;

import java.util.HashMap;
import java.util.Map;

public class AbstractBookFactory
{
  private static Map<String,AbstractBook> bookMap = new HashMap<>();
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
