
package utilities;
import java.math.BigInteger;
import java.security.SecureRandom;
    
/**
 * This program shows some of the inner workings of RSA using the base
 * mathematical functions. You can only use this to test RSA on small strings 
 * of length 16 if the key is N=128. In general a call to RSA(N) can correctly encrypt 
 * Strings of max length N/8. So RSA(512) would work for String of max length 64 
 * RSA is a popular public/private key encryption system. 
 * The public key consists of the modulus n and a public exponent, e, 
 * which is normally set at 65537, as it's a prime number 
 * that is not too large. 
 * The exponent e does not have to be a secretly selected prime number, 
 * as the public key is shared with everyone.
 * 
 * The private key consists of the modulus n and the private exponent d, 
 * which is calculated using the Extended Euclidean algorithm to 
 * find the multiplicative inverse with respect to the totient of n.
 * The totient function , also called Euler's totient function, is defined as 
 * the number of positive integers that 
 * are relatively prime to (i.e., do not contain any factor in common with) , 
 * where 1 is counted as being relatively prime to all numbers.
 * See https://searchsecurity.techtarget.com/definition/RSA
 * 
 * @author Curt Jones (2019)
 */
public class RSA {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;

   // generate an N-bit (roughly) public and private key
   RSA(int N) {
      BigInteger p = BigInteger.probablePrime(N/2, random);
      BigInteger q = BigInteger.probablePrime(N/2, random);
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

      modulus    = p.multiply(q);                                  
      publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
      privateKey = publicKey.modInverse(phi);
   }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() {
      String s = "";
      s += "public  = " + publicKey  + "\n";
      s += "private = " + privateKey + "\n";
      s += "modulus = " + modulus;
      return s;
   }
 
   public static void main(String[] args) {
      int N = 1024;
      RSA key = new RSA(N);
      System.out.println(key);
 
      // create random message, encrypt and decrypt, must be  N or smaller
     //   BigInteger message = new BigInteger(N, random);
//OR
      // Create message by converting string to integer
      // Note -- this will only work for short strings of length N/8 like the one below
    
       String s = "Test of a 1234567890123456789012345678901234567890123456789012345";
       System.out.println(s);
       byte[] bytes = s.getBytes();
       BigInteger message = new BigInteger(bytes);


      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt = key.decrypt(encrypt);
      System.out.println("message   = " + message);
      System.out.println("encrypted = " + encrypt);
      System.out.println("decrypted string as an integer= " + decrypt);
      String result = new String(decrypt.toByteArray());
      System.out.println("decrypted string = " + result);
      System.out.println("Max length is "+result.length());
   }
}



