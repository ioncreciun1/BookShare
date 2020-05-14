
import java.util.*;
import java.sql.SQLException;

public interface RegistrantDAO
{
  public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
      int upVotes) throws SQLException, Exception;
  public void delete() throws SQLException;
  public Registrant getRegistrant(String username) throws SQLException;
  public List<Registrant> getRegistrants() throws SQLException;
  public void update(Registrant registrant) throws SQLException;
  public boolean check_User() throws SQLException;
  public boolean check_Email() throws SQLException;
}
