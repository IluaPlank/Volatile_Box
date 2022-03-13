import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    static AtomicBoolean tumbler = new AtomicBoolean(false);
    static User user = new User();
    static Toy toy = new Toy();
    private static boolean stop = true;
    static int counter = 0;
    static final int STOP_THREAD = 4;

    public static void main(String[] args) {
        Thread click = new Thread(() -> {
            while (stop) {
                user.onClick(tumbler);
                counter++;
                if (counter == STOP_THREAD) {
                    Thread.currentThread().interrupt();
                    stop = false;
                }
            }
        });
        Thread notClick = new Thread(() -> {
            while (click.isAlive()) {
                if (tumbler.compareAndSet(true, false)) {
                    toy.OffClick();
                }
            }
        });
        click.start();
        notClick.start();
    }
}
