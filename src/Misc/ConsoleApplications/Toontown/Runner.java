package Misc.ConsoleApplications.Toontown;

import Misc.ConsoleApplications.Toontown.ToonData.Cog;
import Misc.ConsoleApplications.Toontown.ToonData.Gag;
import Misc.ConsoleApplications.Toontown.ToonData.Toon;
import Misc.ConsoleApplications.Toontown.ToonEnumerations.CogBase;
import Misc.ConsoleApplications.Toontown.ToonEnumerations.GagBase;

import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Battle b = new Battle();
        Toon toon = new Toon("Scooby", 20);
        toon.addGag(new Gag(GagBase.Geyser, false));
        toon.addGag(new Gag(GagBase.Cupcake, false));
        Cog cog = new Cog(CogBase.Flunky, 2);

        b.addToon(toon);
        b.addCog(cog);

        System.out.println("Battle!");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("-> Run");
        System.out.println("-> Choose Gag");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        System.out.print("-> ");
        String response = scan.nextLine();

        Gag[] choices = new Gag[4];
        if (response.toLowerCase(Locale.ENGLISH).equals("choose gag")) {
            System.out.println();
            System.out.println("List of gags:");
            int i = 0;
            for (Gag gag : toon.getUniqueGags()) {
                System.out.println(++i + ": " + gag + " [" + toon.getGagCount(gag) + "]");
                choices[i] = gag;
            }

            System.out.println();
            System.out.print("-> ");
            response = scan.nextLine();
            toon.chooseGag(choices[Integer.parseInt(response)]);
            System.out.println("\ndoing battle with " + toon.getChosenGag() + "!");
        }
    }
}
