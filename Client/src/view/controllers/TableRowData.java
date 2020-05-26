package view.controllers;

import javafx.beans.property.*;
import model.Book;

public class TableRowData
{
 // private IntegerProperty orderNumber;
  private StringProperty bookTitle;
  private StringProperty authorName;
  private StringProperty bookLanguage;
  private StringProperty bookCategory;
  private StringProperty username;
  private Book book;

  public TableRowData(Book book)
  {
    //this.orderNumber = new SimpleIntegerProperty(); // Should be getId ???
    this.bookTitle = new SimpleStringProperty(book.getTitle());
    this.authorName = new SimpleStringProperty(book.getAuthor());
    this.bookLanguage = new SimpleStringProperty(book.getLanguage());
    this.bookCategory = new SimpleStringProperty(book.getCategory());
    this.book = book;
  }

//  public IntegerProperty getOrderNumber()
//  {
//    return orderNumber;
//  }
  public Book getBook(){
    return book;
  }
  public StringProperty getBookTitle()
  {
    return bookTitle;
  }

  public StringProperty authorName()
  {
    return authorName;
  }

  public StringProperty bookCategory()
  {
    return bookCategory;
  }

  public StringProperty bookLanguage()
  {
    return bookLanguage;
  }
}
