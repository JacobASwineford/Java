package Misc.CustomClasses.Cipher;

/**
 * Defines the CharacterMismatchException, a custom exception made for the CipherDisk class.
 *
 * @author Jacob Swineford
 */
public class CharacterMismatchException extends Exception {
    CharacterMismatchException(String message) {
        super(message);
    }
}
