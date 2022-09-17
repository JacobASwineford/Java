package JavaDemos.Java1.ch3.ch3a;

/**
 * Demonstrates string concatenation, special syntax for the construction of
 * string objects, several methods of the string class, and escape characters.
 *
 * @author Jacob Swineford
 * @version 9/10/2018
 */
public class StringDemo {

    public static void main(String[] args) {
        // concatenating strings
        System.out.println(5+7); //integers being added together
        System.out.println("5 + 7"); //string literal
        System.out.println("5" + 7); //mixed type expression(the string 5 plus the string 7)
        System.out.println("ruby" + "peach"); // same as "5" + "7"
        System.out.println("pear" + 7);
        System.out.println("5" + 3.865);
        System.out.println();

        int x = 3;
        String str = "quokka"; //GreatestSubstring is called an object reference, immutable class
        System.out.println(str); //quokka
        System.out.println(str.toUpperCase()); //QUOKKA, made into a different string with all capital letters
        System.out.println(str); //quokka

        String str2 = str.toUpperCase();
        System.out.println(str2);
        System.out.println(str);

        str = "Your powers are weak, old man."; //changes the string literal, the code above stays the same up to this point
        System.out.println(str);
        System.out.println(str.replace("p","" +
                "")); //replaces first instance of the string

        int k = str.indexOf("."); //links the location of k with the location of the first instance of the period (stop before that point)
        String str3 = str.toUpperCase().substring(1, k); //string 3 refers to being uppercase, and the substring refers string 3 to specific character locations (starting from 0)
        System.out.println(str3);

        String t = "sadsadsaefefefesdsadasd";
        System.out.println(t.replace("ef", "###"));

        String q1 = str.toUpperCase();
        String q2 = q1.substring(1, k);
        System.out.println(q2);

        System.out.println(str2);

        //escape characters
        System.out.println("*\n**\n***"); //Backslash initiates an escape character \n makes characters appear om the next line

        System.out.println("A\tB\tC"); // \t indicates a space in between characters

        //output she said, "ATTACK"
        System.out.println("she said, \"ATTACK\"");
        System.out.println("she said, \"ATTACK!\"");
        System.out.println("she said, 'ATTACK'");
        System.out.println('L');
        System.out.println("Here is a backslash: \\"); //Treats the backslash
        System.out.println("Here are two backslashs: \\\\");
        System.out.println("Here is a slash /");


    }
}

