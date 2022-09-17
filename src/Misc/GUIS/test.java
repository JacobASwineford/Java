package Misc.GUIS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author Jacob Swineford
 */
public class test {

    private static int count = 0;
    public static void main(String[] args) {
//        System.out.println(count);
//        KeyFrame kf = new KeyFrame(Duration.millis(1000), event -> {
//            count++;
//            System.out.println(count);
//        });
//        Timeline t = new Timeline(kf);
//        t.setCycleCount(10);
//        t.setOnFinished(event -> System.out.println("Final: " + count));
//        t.play();

        class t extends Thread {
            public void run() {
                while (count < 10) {
                    try {
                        sleep(1000);
                        System.out.println(count);
                        count++;
                    } catch (Exception e) {break;}
                }
                System.out.println("Final " + count);
            }
        }
        Thread t = new t();
        t.start();
    }
}
