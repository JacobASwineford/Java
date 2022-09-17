package Misc.ConsoleApplications.Game;

import Misc.ConsoleApplications.Game.Areas.Area;
import Misc.ConsoleApplications.Game.Areas.Meadow;
import Misc.ConsoleApplications.Game.Areas.MeadowEast;
import Misc.ConsoleApplications.Game.Classes.Warrior;
import Misc.ConsoleApplications.Game.Equipment.Weapons.CrappySword;

import java.sql.*;
import java.util.Arrays;

/**
 * the way things should go:
 * 1. player spawns into the world with the default set (crappy)
 * 2. introduction, introduce commands that the player can use
 * 3. let character scan the environment and interact with the environment
 * 4. enemy is fought, drops loot, pick up loot
 * 5. continue?
 *
 * @author Jacob Swineford
 */
public class tester {

    public static void main(String[] args) {
        
    }

    private Area area = new Meadow();
    private Area area2 = new MeadowEast();

    private void setupArea() {
        area.setAdjacent("East", area2);
        area.setAdjacent("west", area);
    }

    private static void databaseStuff() throws Exception {
        String URL = "jdbc:mysql://localhost:3306/adventure?characterEncoding=latin1";
        Connection co = DriverManager.getConnection(URL, "root", "root");
        System.out.println("Connected successfully!");
        ResultSet rs;
        Statement s = co.createStatement();
        rs = s.executeQuery("select * from characters;");

        int cols = rs.getMetaData().getColumnCount();
        String[] columnNames = new String[cols];
        for (int i = 1; i <= cols; i++) {
            columnNames[i - 1] = rs.getMetaData().getColumnName(i);
        }
        System.out.println(Arrays.toString(columnNames));

        while (rs.next()) {
            String name = rs.getString("name");
            String hat = rs.getString("hat");
            System.out.printf("player '%s' has the hat '%s'", name, hat);
        }
        co.close();
    }

}
