package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * Enables a user to input a password and outputs
 * whether that password is safe or not based on 5 rules.
 *
 * The password must contain:
 *
 * 1. at least 8 digits
 * 2. at least 1 numerical digit.
 * 3. at least 1 UpperCase letter.
 * 4. at least 1 lowercase letter.
 * 5. at least 1 character that is neither a letter or a digit.
 *
 * @author Jacob Swineford
 */
public class SafePasswords {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = in.next();
        int passwordLength = password.length();

        if (is8Digits(password) && hasDigits(password, passwordLength)
            && hasLowerLetters(password, passwordLength) &&
                hasUpperLetters(password, passwordLength) &&
                    hasExtraneous(password, passwordLength)) {
            System.out.println("SAFE. ACCEPTED.");
        } else {
            System.out.println("NOT SAFE. REJECTED.");
        }
    }

    /**
     * The password must be at least 8 digits.
     * returns true or false depending on this rule.
     */
    private static boolean is8Digits(String str) {
        int strLength = str.length();
        if (strLength < 8) {
            return false;
        }
        return true;
    }
    /**
     * The password must contain at least one digit.
     * returns true or false depending on this rule.
     */
    private static boolean hasDigits(String str, int length) {
        int location = 0;
        for (int i = 0; i < length; i++) {
            char strChar = str.charAt(location);
            if (Character.isDigit(strChar)) {
                return true;
            }
            location++;
        }
        return false;
    }
    /**
     * The password must contain a lowercase letter of
     * the alphabet.
     * returns true or false based on this rule.
     */
    private static boolean hasLowerLetters(String str, int length) {
        int location = 0;
        for (int i = 0; i < length; i++) {
            char strChar = str.charAt(location);
            if (Character.isLowerCase(strChar)) {
                return true;
            }
            location++;
        }
        return false;
    }
    /**
     * The password must contain an UpperCase letter
     * of the alphabet.
     * returns true or false based on this rule.
     */
    private static boolean hasUpperLetters(String str, int length) {
        int location = 0;
        for (int i = 0; i < length; i++) {
            char strChar = str.charAt(location);
            if (Character.isUpperCase(strChar)) {
                return true;
            }
            location++;
        }
        return false;
    }
    /**
     * The password must contain a character that is
     * neither a letter nor a digit.
     * returns true or false based on this rule.
     */
    private static boolean hasExtraneous(String str, int length) {
        int location = 0;
        int stringLength = str.length();
        int lengthComparison = 0;

        for (int i = 0; i < length; i++) {
            char strChar = str.charAt(location);
            if (Character.isLetterOrDigit(strChar)) {
                lengthComparison++;
            }
            location++;
        }
        if (stringLength != lengthComparison) {
            return true;
        }
          return false;
    }
}
