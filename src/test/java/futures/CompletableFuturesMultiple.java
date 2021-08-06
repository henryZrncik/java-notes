package futures;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
public class CompletableFuturesMultiple {
    CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(new MySupplier<Integer>(2000));
    CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(new MySupplier<Integer>(2000));
    CompletableFuture[] arrayOfCompletableFutures = {
            CompletableFuture.supplyAsync(new MySupplier<Integer>(2000)),
            CompletableFuture.supplyAsync(new MySupplier<Integer>(5000)),
            CompletableFuture.supplyAsync(new MySupplier<Integer>(1000))
    };

    @Test void Combining2(){ // combining 2  (calls don't depend on each other, I need just smt. to do with results )
        var twoCombined = completableFuture1.thenCombine(completableFuture2, (x1, x2) -> x1  +x2 );
    }
    @Test void CombiningNAll() throws ExecutionException, InterruptedException { // combine all futures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(arrayOfCompletableFutures);
        // Parsing result
        CompletableFuture<List<Object>> listOfResponsesFuture = allFutures.thenApply(v ->
                Arrays.stream(arrayOfCompletableFutures)
                        .map( singleFuture -> singleFuture.join())
                        .collect(Collectors.toList())
        );
        var result = listOfResponsesFuture.thenApply( x ->
                x.stream()
                        .filter( val -> (Integer) val > 500 )
                        .count()
        );
        System.out.println(result.get());
    }
    @Test void CombiningNGetFirst() throws ExecutionException, InterruptedException { // combine all future but get only first response.
        CompletableFuture<Object> firstCompletedCompletableFuture = CompletableFuture.anyOf(arrayOfCompletableFutures);
        System.out.println(firstCompletedCompletableFuture.get());
    }



}
