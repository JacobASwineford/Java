/**
 * Class made to test the methods of the dictionary class.
 * More specifically, this class tests the add and lookFor
 * methods in the dictionary class and how descriptions
 * can be added to definitions already defined in the dictionary.
 *
 * @author Jacob Swineford
 */
public class DictionaryTest
{

    public static void main(String[] args)
    {
        Dictionary d = new Dictionary();
        d.add("Jacob", "CS boy");
        d.add("Jacob", "Mathematics boy");
        d.add("BUSTED", "Bloomsburg University Theatrical Educational Drama");
        System.out.println(d.lookFor("Jacob"));
        System.out.println(d.lookFor("BUSTED"));
        if (d.lookFor("Professor").equals(""))
        {
            System.out.println("There is no definition for Professor.");
        }
    }
}
