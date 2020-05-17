
import java.util.*;
import java.sql.SQLException;

public interface UserDAO
{
  public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
      int upVotes) throws SQLException, Exception;
  public void delete() throws SQLException;
  public User getUser(String username) throws SQLException;
  public List<User> getUsers() throws SQLException;
  public void update(User user) throws SQLException;
  public boolean check_User() throws SQLException;
  public boolean check_Email() throws SQLException;
}
