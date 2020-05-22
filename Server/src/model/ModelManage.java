package model;

import Database.BookDAO;
import Database.BookDAOImplementation;
import Database.UserDAO;
import Database.UserDAOImplementation;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeProxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelManage implements Model
{
  private UserDAO user;
  private BookDAO bookDAO;
  private PropertyChangeProxy<String,Book> property;
  public ModelManage() throws SQLException
  {
    this.user = new UserDAOImplementation();
    this.bookDAO = BookDAOImplementation.getInstance();
    this.property = new PropertyChangeProxy<>(this);

  }

  @Override public boolean checkUser(User user) throws SQLException
  {
    return this.user.check_User(user);
  }

  @Override public boolean check_Email(User user)
      throws SQLException
  {
    return this.user.check_Email(user);
  }

  public void registerUser(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo)
      throws Exception
  {
    System.out.println("SERVER MODEL MODEL");
    user.add(Username,passWord,eMail,firstName,lastName,city,contactInfo);
  }

  @Override public User getUser(String username) throws SQLException
  {

    return user.getUser(username);
  }

  @Override public void addBook(Book book) throws SQLException
  {
    bookDAO.add(book.getUsername(),book.getTitle(),book.getAuthor(),book.getLanguage(),book.getDescription(),book.getCategory());
    property.firePropertyChange("book",null,book);
  }

  @Override public ArrayList<Book> allBooks() throws SQLException
  {
    ArrayList<Book> books = (ArrayList<Book>)bookDAO.allBooks();
//    for(int i = 0;i<bookDAO.allBooks().size();i++)
//    {
//      System.out.println(i);
//      books.add(bookDAO.allBooks().get(i));
//    }
    return books;
  }

  @Override public boolean addListener(GeneralListener<String, Book> listener,
      String... propertyNames)
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Book> listener, String... propertyNames)
  {
    return property.removeListener(listener,propertyNames);
  }
}
