package Misc.CustomClasses.Other;

/**
 * Defines the DimensionMismatchException, a custom exception made for the Matrix class.
 *
 * @author Jacob Swineford
 */
class DimensionMismatchException extends Exception
{
    DimensionMismatchException(String message)
    {
        super (message);
    }
}
