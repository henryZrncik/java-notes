package futures;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class FuturesMultiple {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    Callable<Integer> c1 = new MyCompletable<>(2000);
    Callable<Integer> c2 = new MyCompletable<>(5000);
    Callable<Integer> c3 = new MyCompletable<>(1000);
    List<Callable<Integer>> taskList = Arrays.asList(c1, c2, c3);

    @Test void executeMultipleFuturesButWillBeBloc() throws InterruptedException, ExecutionException {
        List<Future<Integer>> futures = executorService.invokeAll(taskList);
        for(Future<Integer> future: futures) System.out.println(future.get());  // if first one takes 20 seconds you won't have result of those before any sooner.
    }
    @Test void callableUsingExecutorCompletion() throws Exception { // task is now started by executor once you submit new Callable
        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<Integer>(executorService);
        List<Callable<Integer>> taskList = Arrays.asList(c1, c2, c3);
        for ( Callable<Integer> f: taskList)  service.submit(f); // now 3 different threads are working  and 4th (main) continues
        System.out.println(service.take().get());   // get the first one (fastest) result (you can take them in for as well)
        executorService.shutdown();
    }



}

