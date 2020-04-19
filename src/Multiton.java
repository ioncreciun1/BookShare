import java.util.HashMap;
import java.util.Map;

public class Multiton {
    private static Map<String, Multiton> allInstances = new HashMap<>();

    private String passWord = "";
    private String eMail = "";
    private String firstName = "";
    private String lastName = "";
    private String city = "";
    private String contactInfo = "";
    private int upVotes = 0;

    private Multiton (String passWord, String eMail, String firstName, String lastName, String city, String contactInfo, int upVotes){
        this.passWord = passWord;
        this.eMail = eMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.contactInfo = contactInfo;
        this.upVotes = upVotes;
    }

    public static Multiton getInstance(String userName){
        Multiton instance = allInstances.get(userName);
        if(instance == null){
            System.out.println("ERROR - No User Found");
        }
        return instance;
    }

    public static void addInstance(String userName, String passWord, String eMail, String firstName, String lastName, String city, String contactInfo, int upVotes){
        Multiton instance = new Multiton(passWord, eMail, firstName, lastName, city, contactInfo, upVotes);
        allInstances.put(userName, instance);
    }

    public String getPassWord() {
        return passWord;
    }

    public String getEMail() {
        return eMail;
    }

    public String getName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public int getUpVotes() {
        return upVotes;
    }
}
