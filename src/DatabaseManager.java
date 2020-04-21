import java.sql.*;

public class DatabaseManager

{
  public DatabaseManager() throws SQLException{
    DriverManager.registerDriver((new org.postgresql.Driver()));
  }


  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "2011");

  }
  public void add_User(String Username, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo,
      int upVotes) throws SQLException
  {
    try(Connection connection = getConnection()/*auto closes the connection*/){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".registrant (Username, Pass, EMAIL, fName, lName, City, ContactInfo, Upvotes) VALUES (?,?,?,?,?,?,?,?);");
    statement.setString(1, Username);
    statement.setString(2, passWord);
    statement.setString(3, eMail);
    statement.setString(4, firstName);
    statement.setString(5, lastName);
    statement.setString(6, city);
    statement.setString(7, contactInfo);
    statement.setInt(8,upVotes );
      statement.executeUpdate();

    }
   catch ( Exception e ) {
  System.err.println( e.getClass().getName()+": "+ e.getMessage() );
  System.exit(0);
}
       System.out.println("Database query ok ");

  }


}
