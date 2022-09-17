package Misc.CustomClasses.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;

/**
 *
 * @author Curt Jones
 */
/*
 *  Every implementation of the Java platform is required to support the
 *  following standard MessageDigest algorithms:

    MD5
    SHA-1
    SHA-256

* These algorithms are described in the MessageDigest section of the
* Java Cryptography Architecture Standard Algorithm Name Documentation.
* Consult the release documentation for your implementation to see if any other
* algorithms are supported.
*
*/
public class SecurityCode {
    /**
     * Encrypts a string in hexadecimal format using the SHA-256 hash algorithm.
     * If SHA-256 does not exist, then the original string is returned. This is
     * a cryptographic hash function, designed not to be decrypted. Use this
     * concept to store passwords in a database
     *
     * @param originalString The original string.
     * @return The encrypted string.
     */
    public static String encryptSHA256(String originalString) {
        byte[] digest = null;
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            /*
               UTF stands for Unicode Transformation Format.
               The '8' means it uses 8-bit blocks to represent a character.
               UTF-8 - uses 1 byte to represent characters in the ASCII set
            */
            md.update(originalString.getBytes("UTF-8"));
            digest = md.digest();
            for (int i = 0; i < digest.length; i++) {
                if ((0xff & digest[i]) < 0x10) {
                    hexString.append("0");
                }
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return originalString;
        }
        return hexString.toString();
    }

    public static String generateSalt() {
        /* 128 bits for strong security */
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
         /*
          BigInteger(130, random) -- a random integer between 0 and 2^130 -1
          toString(32) -> We are getting the string representation of the number
          in base 32 instead of the traditional base 10. If you passed 16, you
          would get the number in hexadecimal format, with digits going from 0 to f.
          If you passed 8, you would get it as an octal number, with digits going from 0 to 7.
          toString(32) --> 32 = 2^5, so each character will take 5 of the 130 bits.
          130/5 = 26.  So 130 bits will produce 26 characters if you use 5 bits to
          determine each character.

         */
    }

    /**
     * Encrypts a string in hexadecimal format using the SHA-1 hash algorithm.
     * If SHA-1 does not exist, then the original string is returned. This is a
     * cryptographic hash function, designed not to be decrypted. Use this
     * concept to store passwords in a database
     *
     * @param originalString The original string.
     * @return The encrypted string.
     */
    public static String encryptSHA1(String originalString) {
        String alg = "SHA-1";
        byte[] bytes = null;
        try {
            // gets bytes from encryption algorithm
            bytes = MessageDigest.getInstance(alg).digest(originalString.getBytes());
        } catch (NoSuchAlgorithmException e) {
            return originalString;
        }

        // translates bytes to hex string
        StringBuilder hexStrBuf = new StringBuilder();
        for (byte b : bytes) {
            String str = Integer.toHexString(b & 0xff);
            hexStrBuf.append(str.length() == 1 ? "0" : "").append(str);
        }

        return hexStrBuf.toString();
    }

    /**
     * Encrypts a string in hexadecimal format using the MD5 hash algorithm.
     * If MD5 does not exist, then the original string is returned. This is a
     * cryptographic hash function, designed not to be decrypted. Use this
     * concept to store passwords in a database
     *
     * @param originalString The original string.
     * @return The encrypted string.
     */
    public static String encryptMD5(String originalString) {
        String alg = "MD5";
        byte[] bytes = null;
        try {
            // gets bytes from encryption algorithm
            bytes = MessageDigest.getInstance(alg).digest(originalString.getBytes());
        } catch (NoSuchAlgorithmException e) {
            String msg = "The encryption algorithm " + alg
                    + " is not available or does not exist.";
            return originalString;
        }

        // translates bytes to hex string
        StringBuilder hexStrBuf = new StringBuilder();
        for (byte b : bytes) {
            String str = Integer.toHexString(b & 0xff);
            hexStrBuf.append(str.length() == 1 ? "0" : "").append(str);
        }

        return hexStrBuf.toString();
    }


    /**
     * A method that when given a hashed string will determine the hashing algorithm used
     * to generate the string based on the length of the string.
     * The legal algorithms are MD5 or SHA-1 or SHA-256.
     */
    public static String determineHashAlgorithm(String hashedValue){
        String algorithm = null;
        if(hashedValue.length()== 32)return "MD5";
        if (hashedValue.length()== 40)return "SHA-1";
        if (hashedValue.length()== 64)return "SHA-256";
        return algorithm;
    }

    /**
     * Encrypt the given string based on the name of the one way hashing algorithm
     * specified. Legal algorithms are MD5, SHA-1, SHA-256.
     *
     * @param originalString The string to hash.
     * @param algorithm The algorithm to use to perform the hashing.
     * @return The hashed string if a legal algorithm is specified, otherwise
     * the original string.
     */

    public static String encrypt(String originalString, String algorithm) {
        if(originalString == null || algorithm == null ) return originalString;
        String encryptedString = null;
        if(algorithm.equalsIgnoreCase("MD5"))return encryptMD5(originalString);
        if(algorithm.equalsIgnoreCase("SHA-1"))return encryptSHA1(originalString);
        if(algorithm.equalsIgnoreCase("SHA-256"))return encryptSHA256(originalString);
        return encryptedString; //Return the original string if not one of our 3 algorithms.
    }

    /**
     * Encrypts a string in hexadecimal format using the SHA-256 hash algorithm.
     * If SHA-256 does not exist, then the original string is returned. This is
     * a cryptographic hash function, designed not to be decrypted. Use this
     * concept to store passwords in a database
     *
     * @param s The original string.
     * @return The encrypted string.
     */
    public static String hashString(String s) {
        byte[] digest = null;
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes("UTF-8"));
            digest = md.digest();
            for (int i = 0; i < digest.length; i++) {
                if ((0xff & digest[i]) < 0x10) {
                    hexString.append("0");
                }
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return s;
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String randomSalt  = generateSalt();
        System.out.println("\n\nOne way encryption (Hashing) algorithms");
        System.out.println("encrypt with SHA-256(admin1234)  is " + hashString("admin1234"));
        System.out.println("The Salt is me9ehohb79iobanooq0etdnc7q");
        System.out.println("encrypt with SHA-256 (me9ehohb79iobanooq0etdnc7qadmin1234)is " +
                hashString( "me9ehohb79iobanooq0etdnc7qadmin1234"));
        System.out.println("encrypt eith SHA-256(admin1234me9ehohb79iobanooq0etdnc7q)is " +
                hashString( "admin1234me9ehohb79iobanooq0etdnc7q"));
        System.out.println("A random salt is "+randomSalt);

        String password = "goodpassword";
        System.out.println("\nGenerating for a student: ");
        System.out.println("The password is " + password);
        System.out.println("The salt is " + randomSalt);
        System.out.println("The appended unhashed password is " + password + randomSalt);
        System.out.println("The hashed password is " + hashString(password + randomSalt));


    }
}

