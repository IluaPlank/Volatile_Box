import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    static volatile boolean tumbler = false;
    final static int timeout = 3000;
    static int counter = 0;
    final static  int STOP_THREAD = 4;
    private static boolean stop = true;

    public static void main(String[] args) {
        Thread click = new Thread(() -> {
            while (stop) {
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Включить тумблер");
                tumbler = true;
                counter++;
                if (counter == STOP_THREAD) {
                    Thread.currentThread().interrupt();
                    stop = false;
                }
            }
        });

        Thread notClick = new Thread(() -> {
            while (click.isAlive()) {
                if (tumbler) {
                    System.out.println("Выключить тумблер");
                    tumbler = false;
                }
            }
        });

        click.start();
        notClick.start();
    }
}
