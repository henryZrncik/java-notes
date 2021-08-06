package futures;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
public class CompletableFuturesExceptions {
    @Test void ExceptionPropagation() throws ExecutionException, InterruptedException {
        var r = CompletableFuture
            .supplyAsync(() -> "A") // RUNS
            .thenApply(result -> result + "B" ) // RUNS
            .thenApply(result -> { throw new IllegalArgumentException("Age can not be negative"); })    // after this some "catch will be looked for"
            .thenApply(result -> result + "C" ) // SKIPPED
            .exceptionally( (e) -> "NEW_")  // GOES ONLY IF EXCEPTION OCCURS
            .thenApply(result -> result + "D" ); // RUNS
        System.out.println(r.get());    // NEW_D
    }
    @Test void Handler() throws ExecutionException, InterruptedException {  // called in BOTH flows (Exception occurs or not)
        var r = CompletableFuture
                .supplyAsync(()-> "A")
                .handle((result, err) -> err != null ? result + "_OK" : result + "_BAD");
        System.out.println(r.get());
    }


}