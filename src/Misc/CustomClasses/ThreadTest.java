package Misc.CustomClasses;

/**
 * @author Jacob Swineford
 *
 * This does not EXACTLY work.
 */
public class ThreadTest {

    public static void main(String[] args) {
        Status s = new Status("none");
        Thread t = new Thd(s);
        Thread t2 = new Thd2(s);
        t.start();
        t2.start();
    }
}

class Thd extends Thread {

    private Status s;

    public Thd(Status s)
    {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("no");
            }
            s.setStatus("Ping" + " -");
            System.out.println(s.getStatus());
        }
    }
}

class Thd2 extends Thread {

    private Status s;

    public Thd2(Status s)
    {
        this.s = s;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1001);
            } catch (InterruptedException e) {
                System.out.println("no");
            }
            s.setStatus("Pong");
            System.out.println(s.getStatus() + " --");
        }
    }
}

class Status {

    private String status;

    public Status(String status)
    {
        this.status = status;
    }

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
}
