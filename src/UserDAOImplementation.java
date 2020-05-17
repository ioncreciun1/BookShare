import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAOImplementation implements UserDAO
{
  User user = null;
  public UserDAOImplementation(User user) throws SQLException{
    DriverManager.registerDriver((new org.postgresql.Driver()));
    this.user = user;
    }


private Connection getConnection() throws SQLException
    {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2011");

    }

    public boolean check_User() throws SQLException
{
  try(Connection connection = getConnection()/*auto closes the connection*/){
    Statement stm = connection.createStatement();
    ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".\"User\";");
    while(rs.next())
    {
      String usernameGet = rs.getString("Username");
      if (usernameGet.equals(user.getUserName())){
        System.out.println("Username already exists!");
        return true;
      }
    }
    System.out.println("Username added to database!");
    return false;
  }
  catch ( Exception e ) {
    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
    System.exit(0);
    return false;
  }
}

  public boolean check_Email() throws SQLException
  {
    try (Connection connection = getConnection()/*auto closes the connection*/)
    {
      Statement stm = connection.createStatement();
      ResultSet rs = stm.executeQuery("SELECT * FROM \"SEP2\".\"User\";");
      while (rs.next())
      {
        String emailGet = rs.getString("Email");
        if (emailGet.equals(user.getEMail()))
        {
          System.out.println("Email already in use!");
          return true;
        }
      }
      System.out.println("User added to the database!");
      return false;
    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
      return false;
    }
  }
public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
    int upVotes) throws Exception,SQLException
  {try
      (Connection connection = DatabaseConnection.getConnection()/*auto closes the connection*/)
  {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".\"User\" (Username, Pass, EMAIL, fName, lName, City, ContactInfo, Upvotes) VALUES (?,?,?,?,?,?,?,?);");
    /*lines 22-30 adds the registrant to the database*/
    statement.setString(1, user.getUserName());
    statement.setString(2, user.getPassWord());
    statement.setString(3, user.getEMail());
    statement.setString(4, user.getName());
    statement.setString(5, user.getLastName());
    statement.setString(6, user.getCity());
    statement.setString(7, user.getContactInfo());
    statement.setInt(8, user.getUpVotes());
    statement.executeUpdate();
    }
  catch ( Exception e ) {
    System.err.println( e.getClass().getName()+": "+ e.getMessage());
    throw e;
  }
    System.out.println("Registration completed");


  }



  @Override public void delete() throws SQLException
  {
    try(Connection connection = getConnection()/*auto closes the connection*/){
      Statement stm = connection.createStatement();
     String sql = "DELETE FROM \"SEP2\".\"User\" WHERE Username = "+ user.getUserName()+";";
     stm.executeUpdate(sql);
     stm.close();
      System.out.println("Username not found in database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);
    }
  }

  @Override public User getUser(String username) throws SQLException
  {
    {
      try(Connection connection = getConnection()/*auto closes the connection*/){
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".\"User\" WHERE Username = "+ user
            .getUserName()+";");
        while(rs.next())
        {
          User user = new User(rs.getString("Username"),rs.getString("Pass"),rs.getString("EMAIL"),rs.getString("fName"),rs.getString("lName"),rs.getString("City"),rs.getString("ContactInfo"),rs.getInt("Upvotes"));
        }
        System.out.println("Retrieved user!");
      }
      catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      return user;
    }
  }

  @Override public List<User> getUsers() throws SQLException
  {
    List<User> users = new ArrayList<>();
    try(Connection connection = getConnection()/*auto closes the connection*/){
      Statement stm = connection.createStatement();
      ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".\"User\";");
      while(rs.next())
      {
        User user1 = new User(rs.getString("Username"),rs.getString("Pass"),rs.getString("EMAIL"),rs.getString("fName"),rs.getString("lName"),rs.getString("City"),rs.getString("ContactInfo"),rs.getInt("Upvotes"));
        users.add(user1);
      }
      System.out.println("Username added to database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);
    }
    return users;
  }

  @Override public void update(User user) throws SQLException
  {
try(Connection connection = getConnection()/*auto closes the connection*/){
    Statement stm = connection.createStatement();
    ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".\"User\" WHERE Username = "+ user
        .getUserName()+";");
    while(rs.next())
    {
    }
    System.out.println("Username added to database!");
  }
  catch ( Exception e ) {
    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
    System.exit(0);
  }
  }
}
