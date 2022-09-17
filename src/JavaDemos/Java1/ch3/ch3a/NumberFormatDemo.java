package JavaDemos.Java1.ch3.ch3a;

import java.math.BigInteger;
import java.text.NumberFormat;

/**
 * Calculates 2^64 - 1 and outputs the result as a dollar amount with
 * commas to separate thousands.
 *
 * @author Jacob Swineford
 */
public class NumberFormatDemo {

    public static void main(String[] args) {

        // calculate 2^46 - 1
        //BigInteger two = new BigInteger("2");
        //BigInteger x = two.pow(64); // x is the object, much like two. two however is an object connected to a method (static)
        // BigInteger one = new BigInteger("1");
        //BigInteger result = x.subtract(one);

        BigInteger two = new BigInteger("2");
        BigInteger result = two.pow(64).subtract(BigInteger.ONE); //exists for one, zero and ten, and two

        //Static methods are those which belong to its class rather than a particular object

        //If using Java 10, you could do this:
        // BigInteger result = BigInteger.TWO.pow(64).Subtract(BigInteger.ONE);

        // format and output the result for the default locale
        NumberFormat nf = NumberFormat.getCurrencyInstance(); // class that invokes a method (static) that takes the local currency symbol
        System.out.println(nf.format(result)); //formats the result

    }
}
