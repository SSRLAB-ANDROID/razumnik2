package by.ssrlab.razumnik_2_0.Tools;

/**
 * Created by Mihal on 16.10.2017.
 */

 public class Waiter {


    public static void waitInSec(int sec){
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
