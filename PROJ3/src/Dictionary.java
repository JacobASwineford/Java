import java.util.LinkedList;

/**
 * Dictionary class meant to store definitions. This class
 * uses the hashcode method for storing said definitions
 * and always allocates an array defined by MAX_SLOTS.
 *
 * @author Jacob Swineford
 */
public class Dictionary
{

    private LinkedList<Definition>[] data;
    private static int MAX_SLOTS  = 50000;

    public Dictionary()
    {
        data = new LinkedList[MAX_SLOTS];
    }

    /**
     * Returns a hashcode of the word adjusted to the length of data
     * currently allocated by this instance of Dictionary. This is so Dictionary
     * can use the hashcode as an index for faster searching.
     *
     * @param word given word
     * @return adjusted hashcode
     */
    public static int hashCode(String word)
    {
        int hc = word.hashCode();
        if (hc >= MAX_SLOTS)
        {
            hc = hc % MAX_SLOTS;
        }
        return hc;
    }

    /**
     * Adds this word and it's description to this instance of Dictionary.
     * If there is already a definition for the given word, the given
     * description is added to the list of that word's descriptions via
     * the Definition class.
     *
     * @param word given word
     * @param des description for word
     */
    public void add(String word, String des)
    {
        int hc = hashCode(word);
        if (data[hc] == null)
        {
            data[hc] = new LinkedList<>();
        }

        // check if the word is already in the list at data[hc].
        // if so, add the given definition to the list of that definition.
        for (int i = 0; i < data[hc].size(); i++)
        {
            if (data[hc].get(i).getWord().equals(word))
            {
                data[hc].get(i).addDescription(des);
                return;
            }
        }
        data[hc].add(new Definition(word, des));
    }

    /**
     * Looks for the given word's Definition. The string returned will
     * contain the word and it's descriptions. If the word is not
     * part of this Dictionary, then an empty string is returned.
     *
     * @param word given word
     * @return string of word and it's descriptions.
     */
    public String lookFor(String word)
    {
        StringBuilder sb = new StringBuilder();
        int hc = hashCode(word);
        if (data[hc] == null)
        {
            return sb.toString();
        }
        for (int i = 0; i < data[hc].size(); i++)
        {
            if (data[hc].get(i).getWord().equals(word))
            {
                String str = word + ": ";
                sb.append(str);
                for (String des : data[hc].get(i).getDesList())
                {
                    String d = des + "; ";
                    sb.append(d);
                }
            }
        }
        return sb.toString();
    }
}
