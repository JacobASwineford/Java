package JavaDemos.Java1.ch3.ch3a;

import java.math.BigInteger;
import java.net.HttpCookie;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Demonstrates the creation of objects and their initialization with constructors.
 *
 * @author Jacob Swineford
 */
public class ConstructorDemo {

    public static void main(String[] args) {
        Random rand = new Random();
        BigInteger num = new BigInteger("24756865778256748356834");
        HttpCookie cookie = new HttpCookie("username", "jswineford");

        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar birthday = new GregorianCalendar(1963, 10, 17);
    }
}
