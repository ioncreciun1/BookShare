package Database;

import model.User;

import java.util.*;
import java.sql.SQLException;

public interface RegistrantDAO
{
  public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
      int upVotes) throws SQLException, Exception;
  public void delete(User registrant) throws SQLException;
  public User getRegistrant(String username) throws SQLException;
  public List<User> getRegistrants() throws SQLException;
  public void update(User registrant) throws SQLException;
  public boolean check_User(User registrant) throws  SQLException;
  public boolean check_Email(User registrant) throws SQLException;

}