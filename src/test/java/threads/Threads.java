package threads;
import org.junit.jupiter.api.Test;
public class Threads {
    @Test   // always use start instead of run (run just for demonstration, the same thread does the job)
    public void runOrStartThread(){
        class mRunnableCount implements Runnable{   // just local resource
            public int counter;
            public void run() {
                for (int i = 0; i < 100_000_000; i++) counter++;
                System.out.println(counter);
            }
        }
        Thread t = new Thread(new mRunnableCount(), "someGoodNameOfThread");
        t.start();
        System.out.println("DONE");
    }
    @Test // Spawned Thread must end before main.
    public void join() throws InterruptedException {
        class MRunnableSleep implements Runnable { // just local resource
            public void run() {
                System.out.println("started thread: " + Thread.currentThread().getName());
                try {   Thread.sleep(2000); } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished thread: " + Thread.currentThread().getName());
            }
        }

        Runnable longTask = new MRunnableSleep();
        Thread t1 = new Thread( new MRunnableSleep(), "3 seconds long task");
        t1.start();
        System.out.println("main is doing his thing");
        t1.join(); // waits for t1 to complete
        System.out.println("now all can finish");
    }
}



