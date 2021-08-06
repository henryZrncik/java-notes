package futures;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
public class CompletableFutures { // return value or Exception
    CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(new MySupplier<Integer>(2000));
    CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(new MySupplier<Integer>(5000));

    @Test void createCompletableFutureViaSupplier() throws InterruptedException, ExecutionException, TimeoutException {
        var x = completableFuture1.get(10000, TimeUnit.MILLISECONDS);
    }
    @Test void runRunnable(){
        CompletableFuture cf = CompletableFuture.runAsync(() -> System.out.println("some task i don't need result of"));
    }
    @Test void ownExecutor() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(new MySupplier<Integer>(1500), executor);
        var x = completableFuture.get();
    }
    @Test void chaining(){ // thenApply get VAL + return VAL, thenAccept get VAL return VOID, thenRun get VOID return VOID.
        CompletableFuture<Void> completableFuture = CompletableFuture
                .supplyAsync(new MySupplier<Integer>(1500))
                .thenApply((Integer x) -> x + 3)
                .thenAccept((Integer x2) -> System.out.println("I don't return anything"));
    }
    @Test void composing(){ // problem of thenApply is that it will deepen the result if it is another Future.
        CompletableFuture<CompletableFuture<java.lang.Integer>> r1 = completableFuture1.thenApply( a -> completableFuture2);
        CompletableFuture<Integer> r2 = completableFuture1.thenCompose( x -> completableFuture2); // therefore we use compose if chain more Future calls.
    }
}
