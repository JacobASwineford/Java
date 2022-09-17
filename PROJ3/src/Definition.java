
import java.util.ArrayList;

/**
 * Holds a word and it's descriptions. This class is intended
 * to be used with the Dictionary class to efficiently store
 * said words and definitions.
 *
 * @author Jacob Swineford
 */
public class Definition
{
    private String word;
    private ArrayList<String> desList;

    public Definition(String word, String des)
    {
        this.word = word;
        desList = new ArrayList<>();
        desList.add(des);
    }

    public String getWord()
    {
        return word;
    }

    public ArrayList<String> getDesList()
    {
        return desList;
    }

    public void addDescription(String des)
    {
        desList.add(des);
    }
}
