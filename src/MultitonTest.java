public class MultitonTest {
    public static void main(String[] args) {
        Multiton.addInstance("bobthebuilder","fromaug","bobthebuilder@robust.com", "Bob", "Builder","Horsens","20849532",0);
        Multiton.addInstance("bobthefamous","listad","bobthefamous@ridi.com", "Bob", "Famous","Copenhagen","20484839",9999);
        Multiton m1 = Multiton.getInstance("bobthebuilder");
        Multiton m2 = Multiton.getInstance("bobthefamous");
        Multiton m3 = Multiton.getInstance("bobthefamous");
        Multiton m4 = Multiton.getInstance("bobthebuilder");

        System.out.println("m1: " + m1.getCity());
        System.out.println("m2: " + m2.getContactInfo());
        System.out.println("m3: " + m3.getContactInfo());
        System.out.println("m4: " + m4.getCity());
    }
}
