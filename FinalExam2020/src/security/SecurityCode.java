
package security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import utilities.ErrorLogger;



/**
 *
 * @author cjones
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
            md.update(originalString.getBytes("UTF-8"));
            digest = md.digest();
            for (int i = 0; i < digest.length; i++) {
                if ((0xff & digest[i]) < 0x10) {
                    hexString.append("0");
                }
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
           ErrorLogger.log(Level.SEVERE, "Error trying to use SHA-256", ex);
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
            String msg = "The encryption algorithm " + alg
                    + " is not available or does not exist.";
//            ErrorLogger.log(Level.SEVERE, msg, e);
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
    
    // The following is code you need to write - -see the final exam take home questions
    /*
    Write a method that when given a hashed string will determine the hashing algorithm used 
    to generate the string. Your method should accept a String containing the hashed 
    value and return either: MD5 or SHA-1 or SHA-256. 
    */
    
    /**
    * A method that when given a hashed string will determine the hashing algorithm used 
    * to generate the string based on the length of the string. 
    * The legal algorithms are MD5 or SHA-1 or SHA-256. 
    */
    public static String determineHashAlgorithm(String hashedValue){  
        String algorithm = null;
        int l = hashedValue.length();
        if (l == 32) algorithm = "MD5";
        if (l == 40) algorithm = "SHA-1";
        if (l == 64) algorithm = "SHA-256";
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
        // test the string algorithm to see if it is one supported. If so,
        // then encrypt encrpytedString using the proper algoithm and return that.
        if (algorithm.equals("MD5")) encryptedString = encryptMD5(originalString);
        if (algorithm.equals("SHA-1")) encryptedString = encryptSHA1(originalString);
        if (algorithm.equals("SHA-256")) encryptedString = encryptSHA256(originalString);
        return encryptedString; //Return Null if not one of our 3 algorithms. 
    }
    
}
