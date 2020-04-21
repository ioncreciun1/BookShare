import java.sql.SQLException;

public class RegistrantTest
{
    public static void main(String[] args) throws SQLException
    {

        DatabaseManager dm = new DatabaseManager();
        Registrant
            .addInstance("bobthebuilder","fromaug","bobthebuilder@robust.com", "Bob", "Builder","Horsens","20849532",0);
        Registrant
            .addInstance("bobthefamous","listad","bobthefamous@ridi.com", "Bob", "Famous","Copenhagen","20484839",9999);
        Registrant m1 = Registrant.getInstance("bobthebuilder");
        Registrant m2 = Registrant.getInstance("bobthefamous");
        Registrant m3 = Registrant.getInstance("bobthefamous");
        Registrant m4 = Registrant.getInstance("bobthebuilder");

        System.out.println("m1: " + m1.getCity());
        System.out.println("m2: " + m2.getContactInfo());
        System.out.println("m3: " + m3.getContactInfo());
        System.out.println("m4: " + m4.getCity());

        dm.add_User("bobthebuilder","fromaug","bobthebuilder@robust.com", "Bob", "Builder","Horsens","20849532",0);
    }
}
