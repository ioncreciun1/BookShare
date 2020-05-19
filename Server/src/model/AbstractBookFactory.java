package model;

import java.util.HashMap;
import java.util.Map;

public class AbstractBookFactory
{
  private static Map<String,AbstractBook> bookMap = new HashMap<>();
  public static AbstractBook getBookFly(String title,String author)
  {
    AbstractBook book = bookMap.get(title);
    if(book == null)
    {
      synchronized (bookMap)
      {
        book = bookMap.get(title);
        if (book == null)
        {
          book = new BookFly(title, author);
          bookMap.put(title, book);
        }
      }
    }
    return  book;
  }
}
