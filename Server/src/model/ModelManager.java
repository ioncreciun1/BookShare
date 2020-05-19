package model;

import Database.BookDAO;
import Database.BookDAOImplementation;
import Database.UserDAO;
import Database.UserDAOImplementation;
import utility.observer.listener.GeneralListener;

import java.sql.SQLException;

public class ModelManager implements Model
{
  UserDAO user;
  BookDAO bookDAO;
  public ModelManager() throws SQLException
  {
    this.user = new UserDAOImplementation();
    this.bookDAO = new BookDAOImplementation();
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
    user.add(Username,passWord,eMail,firstName,lastName,city,contactInfo,0);
  }

  @Override public User getUser(String username) throws SQLException
  {

    return user.getUser(username);
  }

  @Override public void addBook(Book book) throws SQLException
  {
    System.out.println("Server Model");
  bookDAO.add(book.getUsername(),book.getTitle(),book.getAuthor(),book.getLanguage(),book.getDescription(),book.getCategory());
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return false;
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return false;
  }
}
