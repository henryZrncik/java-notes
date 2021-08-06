package threads;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ExecutorsClass {
    @Test
    public void executorService(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = () ->   System.out.println("Inside : " + Thread.currentThread().getName());
        var x = executorService.submit(runnable);
        executorService.shutdown();
    }
}
