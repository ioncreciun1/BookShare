import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RegistrantDAOImplementation implements RegistrantDAO
{
  Registrant registrant = null;
  public RegistrantDAOImplementation(Registrant registrant) throws SQLException{
    DriverManager.registerDriver((new org.postgresql.Driver()));
    this.registrant = registrant;
    }


private Connection getConnection() throws SQLException
    {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2011");

    }

    public boolean check_User() throws SQLException
{
  try(Connection connection = getConnection()/*auto closes the connection*/){
    Statement stm = connection.createStatement();
    ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".registrant;");
    while(rs.next())
    {
      String usernameGet = rs.getString("Username");
      if (usernameGet.equals(registrant.getUserName())){
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

public void add(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
    int upVotes) throws Exception,SQLException
  {try
      (Connection connection = DatabaseConnection.getConnection()/*auto closes the connection*/)
  {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".registrant (Username, Pass, EMAIL, fName, lName, City, ContactInfo, Upvotes) VALUES (?,?,?,?,?,?,?,?);");
    /*lines 22-30 adds the registrant to the database*/
    statement.setString(1, registrant.getUserName());
    statement.setString(2, registrant.getPassWord());
    statement.setString(3, registrant.getEMail());
    statement.setString(4, registrant.getName());
    statement.setString(5, registrant.getLastName());
    statement.setString(6, registrant.getCity());
    statement.setString(7, registrant.getContactInfo());
    statement.setInt(8,registrant.getUpVotes());
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
     String sql = "DELETE FROM \"SEP2\".registrant WHERE Username = "+registrant.getUserName()+";";
     stm.executeUpdate(sql);
     stm.close();
      System.out.println("Username not found in database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);
    }
  }

  @Override public Registrant getRegistrant(String username) throws SQLException
  {
   return null;
  }

  @Override public List<Registrant> getRegistrants() throws SQLException
  {
    List<Registrant> registrants = new ArrayList<>();
    try(Connection connection = getConnection()/*auto closes the connection*/){
      Statement stm = connection.createStatement();
      ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".registrant;");
      while(rs.next())
      {
        Registrant registrant1 = new Registrant(rs.getString("Username"),rs.getString("Pass"),rs.getString("EMAIL"),rs.getString("fName"),rs.getString("lName"),rs.getString("City"),rs.getString("ContactInfo"),rs.getInt("Upvotes"));
        registrants.add(registrant1);
      }
      System.out.println("Username added to database!");
    }
    catch ( Exception e ) {
      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
      System.exit(0);
    }
    return registrants;
  }

  @Override public void update(Registrant registrant) throws SQLException
  {
try(Connection connection = getConnection()/*auto closes the connection*/){
    Statement stm = connection.createStatement();
    ResultSet rs = stm.executeQuery( "SELECT * FROM \"SEP2\".registrant WHERE Username = "+registrant.getUserName()+";");
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
