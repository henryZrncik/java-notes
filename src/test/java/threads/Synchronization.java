package threads;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
public class Synchronization {
    /** Thread safe (just add synchronized) otherwise race condition possible * */
    class ThreadSafeCounter {
        public int counter;
        public synchronized void incrementCounter(){
            this.counter = this.counter + 1 ;
        }
    }

    @Test
    public void synchronizedDemo() throws InterruptedException {
        ThreadSafeCounter mc = new ThreadSafeCounter();
        List<Thread> listOfThreads = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    mc.incrementCounter();
                }
            });
            listOfThreads.add(t);
            t.start();
        }

        for (Thread t: listOfThreads)  t.join(); // waiting for all threads to join
        System.out.println(mc.counter);

    }

}
