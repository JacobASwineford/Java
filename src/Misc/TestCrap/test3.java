package Misc.TestCrap;

/**
 * @author Jacob Swineford
 */
public class test3 {

    public static void main(String[] args) {
        try {
            Thread t = new Print("This is child thread 1", 1000);
            Thread t2 = new Print("This is child thread 2", 5000);
            t.start();
            t2.start();
            t.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("This is a parent thread");
    }

    static class Print extends Thread {

        private String mes;
        private int time;


        Print(String mess, int t) {
            mes = mess;
            time = t;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(time);
                System.out.println(mes);
            } catch (Exception e) {
                //
            }
        }
    }
}
