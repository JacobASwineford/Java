
package finalExam;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import security.SecurityCode;


/**
 *
 * @author cjones
 */
public class FinalExam2020 {
    final static Charset ENCODING = StandardCharsets.UTF_8;
    
    public static final String LegalCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LegalDigitsAndSpecialSymbols = "1234567890!$%#*@?><&";
    public static final String AllLegalPasswordCharacters = LegalCharacters+LegalDigitsAndSpecialSymbols;
    public static final String AllLegalSaltCharacters = AllLegalPasswordCharacters;
    
    private static Set <String> dictionary; //A set of common words
    private static Set <String> commonPasswords;//A set of common passwords 
    private static Map <String,Password> rainbowTableSH1; //The hash value is the key, the SH-1 algorithm is used 
    private static Map <String,Password> rainbowTableSH256; //The hash value is the key, the SH-256 algorithm is used 
    private static Map <String,Password> rainbowTableMD5; //The hash value is the key, the MD5 algorithm is used 
    
    public static void main(String args[]) {
        
        System.out.println("All legal lengths (p,s): " + AllLegalPasswordCharacters.length() + " , " + AllLegalSaltCharacters.length());
  
    try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println( "Error setting the GunGame look and feel.");
            ex.printStackTrace(); // show the entire error message
        }
        utilities.Stopwatch timer1 =new utilities.Stopwatch();
        timer1.start();
    // Step 1 -- read the common passwords file and put it into the commonPasswords Set
        createCommonPasswordSet(); 
    // Step 2 -- read the common dictionary file and put it into the commonDictionary Set
        createDictionarySet();
    //Step 3 -- create the rainbow tables
        createRainbowTables();
    //Now the final exam questions
        double time1 = timer1.elapsedTime();
        System.out.printf("Time to load the data files was (%.2f seconds)\n", time1);
        timer1.start();   
////        TakeHomeQuestion2();
        System.out.printf("Time to complete question 2 was (%.2f seconds)\n", time1);
//        timer1.start();
//        Question3();
//        time1 = timer1.elapsedTime();
//        System.out.printf("Time to complete question 3 was (%.2f seconds)\n", time1);
//        timer1.start();
//        Question8();
//        time1 = timer1.elapsedTime();
//        System.out.printf("Time to complete question 8 was (%.2f seconds)\n", time1);
//        timer1.start();
//        Question9();
//        time1 = timer1.elapsedTime();
//        System.out.printf("Time to complete question 9 was (%.2f seconds)\n", time1);
//        timer1.start();
//        Question10();
//        time1 = timer1.elapsedTime();
//        System.out.printf("Time to complete question 10 was (%.2f seconds)\n", time1);
//        timer1.start();
//        SecurityCode.encrypt("Password", "MD5");
//        time1 = timer1.elapsedTime();
//        System.out.println("Time for question 11 is: " + time1);
//        timer1.start();
//        determineOriginalStringNoSaltFromFiles("dc647eb65e6711e155375218212b3964");
//        time1 = timer1.elapsedTime();
//        System.out.println("The time for question 12 is: " + time1);
        
    }
    
    private static void createRainbowTables(){
       rainbowTableSH1 = new TreeMap<>();  
       rainbowTableSH256 = new TreeMap<>();
       rainbowTableMD5 = new TreeMap<>();
       
       Iterator<String> it = dictionary.iterator();
       Password password = new Password("","");
       while (it.hasNext()){
           String word = it.next();
           password = new Password(word,SecurityCode.encryptSHA1(word));
           rainbowTableSH1.put(password.hashValue, password);
           password = new Password(word,SecurityCode.encryptSHA256(word));
           rainbowTableSH256.put(password.hashValue, password);
           password = new Password(word,SecurityCode.encryptMD5(word));
           rainbowTableMD5.put(password.hashValue, password);
       }
       
       it = commonPasswords.iterator();
       while (it.hasNext()){
           String word = it.next();
           password = new Password(word,SecurityCode.encryptSHA1(word));
           rainbowTableSH1.put(password.hashValue, password);
           password = new Password(word,SecurityCode.encryptSHA256(word));
           rainbowTableSH256.put(password.hashValue, password);
           password = new Password(word,SecurityCode.encryptMD5(word));
           rainbowTableMD5.put(password.hashValue, password);
       }
       System.out.println("The size of rainbowTableSH1 is "+ rainbowTableSH1.size() );
       System.out.println("The size of rainbowTableSH256 is "+ rainbowTableSH256.size() );
       System.out.println("The size of rainbowTableMD5 is "+ rainbowTableMD5.size() );
    }
        
    private static void createCommonPasswordSet() {
        commonPasswords = new TreeSet();
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the common passwords file"); // optional setting
        int returnValue = fileChooser.showDialog(null,"Open Passwords File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        
        try {
            Scanner commonPasswordsInput = new Scanner(file);
            int count = 0;
            String word = null;
            while(commonPasswordsInput.hasNext()){
                 word = commonPasswordsInput.next();
                commonPasswords.add(word.trim());
                if(count==0)System.out.println("The first word was %"+word+"%" );
                count++;
            }
            System.out.println("Common Passwords has "+count+" entries in it" );
            System.out.println("The last word was %"+word+"%" );
            System.out.println("The number of unique words in the common password file was "+ commonPasswords.size() );
        } catch (FileNotFoundException ex) {
            System.out.println( "Error opening the common passwords file.");
            ex.printStackTrace(); // show the entire error message
        }
    }
    
    private static void createDictionarySet() {
        dictionary = new TreeSet();
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the dictionary file"); // optional setting
        int returnValue = fileChooser.showDialog(null,"Open Dictionary File"); 
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        
        try {
            Scanner commonPasswordsInput = new Scanner(file);
            int count = 0;
            String word = null;
            while(commonPasswordsInput.hasNext()){
                 word = commonPasswordsInput.next();
                dictionary.add(word.trim());
                if(count==0)System.out.println("The first word was %"+word+"%" );
                count++;
            }
            System.out.println("dictionary has "+count+" entries in it" );
            System.out.println("The last word was %"+word+"%" );
            System.out.println("The number of unique words in the dictionary was "+ dictionary.size() );
        } catch (FileNotFoundException ex) {
            System.out.println( "Error opening the dictionary file.");
            ex.printStackTrace(); // show the entire error message
        }
    }

    private static void TakeHomeQuestion2() {
            String password = "";
            String salt = "";
            String algo = ""; 
            String en = "3f77822bb111425e5ugom3qgs8s87oa36vljfqfflr";
        
//            String password = "2454mark";
//            String salt = "";
//            String algo = ""; 
//            String en = "7b1da4740a31c09947752a16f32163eba6591e9c0ec8639597bd4376b3faa255";

            // looking in common words
            for (String word : commonPasswords) {
                String e1;
                String e256;
                String e5;
                if (salt.equals("")) {
                    e1 = SecurityCode.encryptSHA1(password + word);
                    e256 = SecurityCode.encryptSHA256(password + word);
                    e5 = SecurityCode.encryptMD5(password + word);                    
                } else {
                    e1 = SecurityCode.encryptSHA1(word + salt);
                    e256 = SecurityCode.encryptSHA256(word + salt);
                    e5 = SecurityCode.encryptMD5(word + salt);    
                }

                if (en.equals(e1)) {
                    algo = "SHA1";
                    if (password.equals("")) password = word;
                    break;
                }
                if (en.equals(e256)) {
                    algo = "SHA256";
                    if (password.equals("")) password = word;
                    if (salt.equals("")) salt = word;
                    break;
                }                
                if (en.equals(e5)) {
                    algo = "MD5";
                    if (password.equals("")) password = word;
                    if (salt.equals("")) salt = word;
                    break;
                }
            }
            
            // looking in dictionary
            if (password.equals("") || salt.equals("")) {
                for (String word : dictionary) {
                    String e1;
                    String e256;
                    String e5;
                    if (salt.equals("")) {
                        e1 = SecurityCode.encryptSHA1(password + word);
                        e256 = SecurityCode.encryptSHA256(password + word);
                        e5 = SecurityCode.encryptMD5(password + word);                    
                    } else {
                        e1 = SecurityCode.encryptSHA1(word + salt);
                        e256 = SecurityCode.encryptSHA256(word + salt);
                        e5 = SecurityCode.encryptMD5(word + salt);    
                    }

                    if (en.equals(e1)) {
                        algo = "SHA1";
                        if (password.equals("")) password = word;
                        break;
                    }
                    if (en.equals(e256)) {
                        algo = "SHA256";
                        if (password.equals("")) password = word;
                        if (salt.equals("")) salt = word;
                        break;
                    }                
                    if (en.equals(e5)) {
                        algo = "MD5";
                        if (password.equals("")) password = word;
                        if (salt.equals("")) salt = word;
                        break;
                    }
                }               
            }
            System.out.println("Password: " + password);
            System.out.println("Salt: " + salt);
            System.out.println("Algorithm: " + algo);
        }
    
    /**
     * This method determines which password from common passwords and 
     * dictionary files provided would generate the supplied hashed password.
     * 
     * The hashing algorithm used is determined from the hashed password passed to this method. 
     * 
     * If the password that would generate this hash password 
     * is not in our files, then we return null;
     * 
     * @param hashedPassword The hashed value of the password.
     * 
     * @return The original password or null if the password is not in our files.
     */
    
    public static String determineOriginalStringNoSaltFromFiles(String hashedPassword){
       String en;
       long count = 0;
       String algo = SecurityCode.determineHashAlgorithm(hashedPassword); // determined from parameter
       for (String word : commonPasswords) {
           en = SecurityCode.encrypt(word, algo);
           count++;
           if (en.equals(hashedPassword)) {
               System.out.println("Count: " + count);
               return word;
           }
       }
       
       for (String word : dictionary) {
           en = SecurityCode.encrypt(word, algo);
           count++;
           if (en.equals(hashedPassword)) {
               System.out.println("Count: " + count);
               return word;
           }
       }
       return null;
    }
   
    private static void Question3() {
            String password = "";
            String e256 = "";
            String en = "4605d77e58d75e79662ac4cc30bac672275c5d5e80e21b0d55db813a22373fe6";
            
            // looking in common words
            for (String word : commonPasswords) {
                e256 = SecurityCode.encryptSHA256(word);
                if (en.equals(e256)) {
                    password = word;
                }
            }
            
            // looking in dictionary
            if (password.equals("")) {
                for (String word : dictionary) {
                    e256 = SecurityCode.encryptSHA256(word);
                    if (en.equals(e256)) {
                        password = word;
                    } 
                }                
            }
            
            System.out.println("Password: " + password);
    }
    
    private static void Question8() {
            String password;
            String e256;
            String en = "55d2c9f63c8c04c7b53b5da3e4add310060737f67851f985d1724e686f08a66b";
            
            BigInteger tested =  new BigInteger("0");
            
            // looking in dictionary
            for (String word : dictionary) {
                if (word.length() > 5) continue; // optimized search
                tested = tested.add(new BigInteger("1"));
                for (String word2 : dictionary) {
                    if (word2.length() != 9) continue; // optimized search
                    tested = tested.add(new BigInteger("1"));
                    e256 = SecurityCode.encryptSHA256(word + " " + word2);  
                    if (en.equals(e256)) {
                        password = word + " " + word2;
                        System.out.println("Password: " + password);   
                        System.out.println("Tested Strings: " + tested);                        
                        return; // optimized search
                    }
                }
            }
    }
   
    private static void Question9() {
            String password;
            String e5;
            String en = "2c53099eefd30630ced717bb83b69913";
            
            BigInteger tested =  new BigInteger("0");
            
            // looking in common passwords
            for (String word : commonPasswords) {
                if (word.toCharArray()[0] != 's') continue; // optimized search
                tested = tested.add(new BigInteger("1"));
                for (String word2 : commonPasswords) {
                    if (word2.toCharArray()[0] != 'p') continue; // optimized search
                    tested = tested.add(new BigInteger("1"));
                    e5 = SecurityCode.encryptMD5(word + word2);  
                    if (en.equals(e5)) {
                        password = word + word2;
                        System.out.println("Password: " + password);   
                        System.out.println("Tested Strings: " + tested);                        
                        return; // optimized search
                    }
                }
            }        
    }
   
    private static void Question10() {
        String password;
        String e256;
        String en = "e9790c7bfafac205aebee0b5d9ab4976cfa5ef46f3d4e5cb7ed57e1216b2acef";
        String values = "efghijklmnopqrstuvwxyz";
        
        int length = 7; // z at the beginning is constant
        int valLength = values.length();
        
        BigInteger base = new BigInteger(Integer.toString(valLength));
        BigInteger numLoops = base.pow(length);
        BigInteger cur = new BigInteger("0");
        
        while (!cur.equals(numLoops)) {
            String str = baseRepOf(cur, valLength, values);
            str = extendTo(str, length, values);
            password = 'z' + str;
            cur = cur.add(new BigInteger("1"));
            e256 = SecurityCode.encryptSHA256(password);
            if (en.equals(e256)) {
                System.out.println("Password: " + password);
                System.out.println("Tested Strings: " + cur);
                return;
            }
        }
    }
   
   /**
     * This method determines which password from common passwords and 
     * dictionary files provided would generate the supplied hashed password.
     * We are assuming that the hashed password was generated by adding the salt to the 
     * end of the original password before applying the hashing algorithm.
     * The hashing algorithm used is determined from the hashed string passed to this method. 
     * We don't return the salted part. So if the hashed value of the string is password+salt 
     * generates the hashed string provided, we return the  password. 
     * If the password is not in our files, then we return null;
     * 
     * @param hashedPassword The hashed string that resulted from hashing a 
     * password from one of our files with a salt appended to the end of the password.  
     * 
     * @param salt The string appended to the end fo the password before the
     * hashing algorithm was applied. 
     * 
     * @return The original string or null if no string was determined to work.
     */
   
   public static String  determineOriginalStringWithSaltromFiles(String hashedPassword , String salt){
       String en = "";
       String algo = SecurityCode.determineHashAlgorithm(hashedPassword);
       
       for (String word : commonPasswords) {
           en = SecurityCode.encrypt(word + salt, algo);
           if (en.equals(hashedPassword)) return word;
       }
       
       for (String word : dictionary) {
           en = SecurityCode.encrypt(word + salt, algo);
           if (en.equals(hashedPassword)) return word;
       }
       return null;
   }
   
     /**
     * This method determines which string would generate the supplied hashedString
     * by utilizing a brute force algorithm. 
     * The hashing algorithm used is determined from the length of the hashed string. 
     * We are assuming that no Salt was used. Start with all strings of length 1 
     * and continue until you have tried all strings of length maxLength. 
     *  
     * @param hashedString The string we are trying to generate by hashing
     * words generated by brute force. 
     * 
     * @param maxLength The maximum length of strings to test. 
     * 
     * @return The original string or null if no string was determined to work.
     */
    
    public static String determineOriginalStringByBruteForce(String hashedString, int maxLength){
       String en;
       String baseRep;
       String algo = SecurityCode.determineHashAlgorithm(hashedString); 
       
       int base = AllLegalPasswordCharacters.length();
       
       BigInteger ba = new BigInteger(Integer.toString(base));
       
       //  im pretty sure that's it, I know the while loop is correct
       for (int length = 1; length <= maxLength; length++) {
           BigInteger cur = new BigInteger("0");
           BigInteger numLoops = ba.pow(length);
           while (!cur.equals(numLoops)) {
               baseRep = baseRepOf(cur, base,  AllLegalPasswordCharacters);
               baseRep = extendTo(baseRep, length, AllLegalPasswordCharacters);
               en = SecurityCode.encrypt(baseRep, algo);
               cur = cur.add(new BigInteger("1"));
               if (en.equals(hashedString)) return baseRep;
            }             
       }
       return null;
   }
    
     /**
     * This method determines which string would generate the supplied hashedString
     * if the salt was appended to the end of the string.
     * A brute force algorithm is used. 
     * The hashing algorithm used is determined from the length of the hashed string. 
     * Start with all strings of length 0 
     * and continue until you have tried all strings of length maxLength. 
     * So if "a"+salt generates the hashedString provided, then return "a". 
     *  
     * @param hashedString The string we are trying to generate by hashing
     * "words" generated by brute force and salted with the given salt.  
     * 
     * @param maxLength The maximum length of strings to test.
     * 
     * @return The original string or null if no string was determined to work.
     */
    
    public static String determineOriginalSaltedStringByBruteForce(String hashedString, String salt, int maxLength){
       String en;
       String baseRep;
       String algo = SecurityCode.determineHashAlgorithm(hashedString); 
       
       int base = AllLegalPasswordCharacters.length();
       
       BigInteger ba = new BigInteger(Integer.toString(base));
       
       //  im pretty sure that's it, the while loop is correct
       for (int length = 1; length <= maxLength; length++) {
           BigInteger cur = new BigInteger("0");
           BigInteger numLoops = ba.pow(length);
           while (!cur.equals(numLoops)) {
               baseRep = baseRepOf(cur, base,  AllLegalPasswordCharacters);
               baseRep = extendTo(baseRep, length, AllLegalPasswordCharacters);
               en = SecurityCode.encrypt(baseRep + salt, algo);
               cur = cur.add(new BigInteger("1"));
               if (en.equals(hashedString)) return baseRep;
           }             
       }
         
       return null;
   }
    
    // algorithms that help determine passwords from a based search.
    // based here meaning all the potential character slots all have the
    // same amount of possibilities. 
    private static String baseRepOf(BigInteger b, int base, String baseValues) {
        String temp = "";
        BigInteger numTemp = b;
        BigInteger ba = new BigInteger(Integer.toString(base));
        BigInteger i;
        while (!numTemp.equals(new BigInteger("0"))) {
            i = numTemp.mod(ba);
            temp += baseValues.toCharArray()[i.intValueExact()];
            numTemp = numTemp.divide(ba);
        }
        return reversed(temp);        
    }
    private static String reversed(String str) {
        String r = "";
        char[] arr = str.toCharArray();
        for (int i = str.length() - 1; i > -1; i--) 
            r += arr[i];
        return r;
    }
    private static String extendTo(String str, int length, String baseValues) {
        int loops = length - str.length();
        String valo = baseValues.substring(0,1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loops; i++) {
            sb.append(valo);
        }
        return sb.toString() + str;
    } 
}
