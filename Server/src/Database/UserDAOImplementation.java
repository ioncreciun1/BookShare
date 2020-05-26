package Database;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAOImplementation implements UserDAO
{
  /**
   * Class constructor registers the given driver with the DriverManager
  * @throws SQLException error JDBC encounters an error during an interaction with a data source, it throws an instance of SQLException
  * If the driver is currently registered, no action is taken. */
  public UserDAOImplementation( ) throws SQLException{
    DriverManager.registerDriver((new org.postgresql.Driver()));

  }

  /**
   * Driver Manager class attempts to establish a connection to the given database URL.
  * @throws SQLException if a database access error occurs or the url is null*/
  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://btv9bsc3ws7gpfnylr1c-postgresql.services.clever-cloud.com:5432/btv9bsc3ws7gpfnylr1c", "uhxksonl4uvuqdftznsg",
            "pnwEWl0YlSc6A2z619ff");

  }
/**checks if the email entered in the sign up form is already in the database, return false and adds the user if it is not already in use
*  @throws SQLException if a database access error occurs or the user is null
* @param user if a database error occurs or the user is null */
  public boolean check_Email(User user) throws SQLException
  {
    try (Connection connection = getConnection()/*auto closes the connection*/)
    {/*A Statement is an interface that represents a SQL statement. You execute Statement objects, and they generate
     ResultSet objects, which is a table of data representing a database result set.
      You need a Connection object to create a Statement object.*/
      Statement stm = connection.createStatement();
      /*To execute a query, call an execute method from Statement, executeQuery: Returns one ResultSet object.*/
      ResultSet rs = stm.executeQuery("SELECT * FROM \"SEP2\".\"user\";");
     /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor. This cursor is a pointer that points to one row of data in the ResultSet object. Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
      while (rs.next())
        /*access the data in a ResultSet object through a cursor. Note that this cursor is not a database cursor. This cursor is a pointer that points to one row of data in the ResultSet object. Initially, the cursor is positioned before the first row. You call various methods defined in the ResultSet object to move the cursor.*/
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
/**
 * @param user if the username entered on the sign up form is in use, returns false if it isn't and registers the user in the database*/
  public boolean check_User(User user) throws SQLException
  {/**@param  connection can use a try-with-resources statement to automatically close Connection, Statement, and ResultSet objects,
  regardless of whether an SQLException has been thrown. An automatic resource statement consists of a try statement and one or more declared resources. */
    try(Connection connection = getConnection()/*auto closes the connection*/){
      /*The following statement is an try-with-resources statement, which declares one resource, stm,
       that will be automatically closed when the try block terminates:*/
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

  /**
   * adds a new user to the database
  * @param Username
   *        is a String username entered by user
   * @param passWord
   *      is a String password  entered by user
   * @param eMail
   *    is a String password  entered by user
   *
   *
   * @param firstName
   *    is a String first name entered by user
   * @param lastName
   *    is a String last name  entered by user
   * @param city
   *    is a String city entered by user
   * @param contactInfo
   *    is a String for contact info entered by user*/
  public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo) throws Exception,SQLException
  {try
      (Connection connection = getConnection()/*auto closes the connection*/)
  {
    User registrant = new User(Username,passWord,eMail,firstName,lastName,city,contactInfo);
    PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".\"User\" (Username, Pass, EMAIL, fName, lName, City, ContactInfo) VALUES (?,?,?,?,?,?,?);");
    /*lines 22-30 adds the registrant to the database using getters from User*/
    statement.setString(1, registrant.getUserName());
    statement.setString(2, registrant.getPassWord());
    statement.setString(3, registrant.getEMail());
    statement.setString(4, registrant.getName());
    statement.setString(5, registrant.getLastName());
    statement.setString(6, registrant.getCity());
    statement.setString(7, registrant.getContactInfo());
    statement.executeUpdate();
  }
  catch ( Exception e ) {
    System.err.println( e.getClass().getName()+": "+ e.getMessage());
    throw e;
  }
    System.out.println("Registration completed");


  }

/**
 * this method deletes a user with a matching
* @param user
 * user.getUserName() in the database*/
  @Override public void delete(User user) throws SQLException
  {
    try(Connection connection = getConnection()/*auto closes the connection*/){
      Statement stm = connection.createStatement();
      String sql = "DELETE FROM \"SEP2\".\"User\" WHERE Username = "+user.getUserName()+";";
      stm.executeUpdate(sql);
      stm.close();
      System.out.println("Username not found in database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);
    }
  }
/** get method to get a User by @param String username
* @return username  with matching username*/
  @Override public User getUser(String username) throws SQLException
  {
    User user = null;
    try (Connection connection = getConnection()/*auto closes the connection*/)
    {
      Statement stm = connection.createStatement();

      String sql = "SELECT * FROM \"SEP2\".\"user\" WHERE Username = '"+username+"';";
      System.out.println(sql);
      ResultSet rs = stm.executeQuery(sql);
      if(rs.next())
      {
        user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
            rs.getString(5), rs.getString(6), rs.getString(7));
        System.out.println("Username added to database!");
        rs.close();
      }
      else{
        user = null;
      }
    }
    catch (Exception e)
    {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);/*why do we need this??*/
    }
    return user;
  }
/**creates a list of users registered in the database
* @throws SQLException exception if there is a database error
* @returns  list of users*/
  @Override public List<User> getUsers() throws SQLException
  {
    List<User> registrants = new ArrayList<>();
    try(Connection connection = getConnection()/*auto closes the connection*/){
      Statement stm = connection.createStatement();
      ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".\"User\";");
      while(rs.next())
      {
        User registrant1 = new User(rs.getString("Username"),rs.getString("Pass"),rs.getString("EMAIL"),rs.getString("fName"),rs.getString("lName"),rs.getString("City"),rs.getString("ContactInfo"));
        registrants.add(registrant1);
      }
      System.out.println("Username added to database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);/*why do we need this??*/
    }
    return registrants;
  }
/**/
  @Override public void update(User user) throws SQLException
  {
    try(Connection connection = getConnection()/*auto closes the connection*/){
      Statement stm = connection.createStatement();
      ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".\"User\" WHERE Username = "+ user.getUserName()+";");
      while(rs.next())
      {
      }
      System.out.println("Username added to database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);/*why do we need this??*/
    }
  }
}