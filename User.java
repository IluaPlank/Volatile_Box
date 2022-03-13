import java.util.concurrent.atomic.AtomicBoolean;

public class User {
    final int timeout = 3000;

    public void onClick(AtomicBoolean tumbler) {
        try {
            if (!tumbler.get()) {
                Thread.sleep(timeout);
                System.out.println("Включить тумблер");
                tumbler.set(true);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
