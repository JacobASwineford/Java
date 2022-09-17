package Misc.TestCrap;

/**
 * @author Jacob Swineford
 */
public class test8 {

    public static void main(String[] args) {
        double L = Math.pow(10, 6);
        double R = 10000;
        double trans = L / R;
        double minutes = trans / 60;
        double hours = minutes / 60;
        System.out.println(trans);
        System.out.println(minutes);
        System.out.println(hours);

        double mb = 8 * Math.pow(10, 6);
        double e = R / mb;
        double r = (.00025 * 3) * 100;
        System.out.println(r);
    }
}
