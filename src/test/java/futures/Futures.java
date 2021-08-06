package futures;

import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Futures {
    Callable<Integer>  asyncWork = new MyCompletable<>(1000);
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Test void callableUsingCall() throws Exception { // Callable is the same as Runnable but I want some response back.
        Integer result  = asyncWork.call(); // done by main + blocking
    }
    @Test void callableUsingThread() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(asyncWork);
        Thread t = new Thread(futureTask);
        t.start();  // another thread is doing job
        Integer result  = futureTask.get(); // blocking and hoping that other thread do its job.
    }
    @Test void callableUsingExecutor() throws ExecutionException, InterruptedException { // task is now started by executor once you submit new Callable
        Future<Integer> result = executorService.submit(new MyCompletable<>(3000));  // another thread just started its job
        Future<Integer> result2 = executorService.submit(new MyCompletable<>(2000));  // another thread just started its job
        System.out.println(result.get());   // main blocked until Future returns result. if this take 10 seconds and second 5 you wait 10 seconds still
        System.out.println(result2.get());   // main blocked until Future returns result.
        // alternatively while(!future.isDone()) { sleep ... sout... }
        executorService.shutdown();
    }

}

