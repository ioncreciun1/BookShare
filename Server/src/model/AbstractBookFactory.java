package model;

import java.util.HashMap;
import java.util.Map;

public class AbstractBookFactory
{
  private static Map<String,AbstractBook> bookMap = new HashMap<>();
  public static AbstractBook getBookFly(String title,String author,String language,String type)
  {
    AbstractBook book = bookMap.get(title);
    if(book == null)
    {
      synchronized (bookMap)
      {
        book = bookMap.get(title);
        if (book == null)
        {
          book = new BookFly(title, author, language, type);
          bookMap.put(title, book);
        }
      }
    }
    return  book;
  }
}
