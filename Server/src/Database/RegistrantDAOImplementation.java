package Database;

import Database.Registrant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RegistrantDAOImplementation implements RegistrantDAO
{

  public RegistrantDAOImplementation() throws SQLException{
    DriverManager.registerDriver((new org.postgresql.Driver()));
  }


  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

  }
  public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
      int upVotes) throws Exception,SQLException
  {try
      (Connection connection = getConnection()/*auto closes the connection*/)
  {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".registrant (Username, Pass, EMAIL, fName, lName, City, ContactInfo, Upvotes) VALUES (?,?,?,?,?,?,?,?);");
    /*lines 22-30 adds the registrant to the database*/
    statement.setString(1, Username);
    statement.setString(2, passWord);
    statement.setString(3, eMail);
    statement.setString(4, firstName);
    statement.setString(5, lastName);
    statement.setString(6, city);
    statement.setString(7, contactInfo);
    statement.setInt(8,upVotes );
    statement.executeUpdate();
    /*line 31 puts the new registrant in memory(in the map)*/
    Registrant.addInstance(Username, passWord, eMail, firstName, lastName, city, contactInfo, upVotes);
  }
  catch ( Exception e ) {
    System.err.println( e.getClass().getName()+": "+ e.getMessage());
    throw e;
  }
    System.out.println("Registration completed");


  }



  @Override public void delete(String username) throws SQLException
  {
    ;
  }

  @Override public Registrant getRegistrant(String username) throws SQLException
  {
    return null;
  }

  @Override public List<Registrant> getRegistrants() throws SQLException
  {
    return null;
  }

  @Override public void update(Registrant registrant) throws SQLException
  {
    ;
  }
}