package parse.streams;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

public class Concepts {
    Stream<Integer> s = Stream.of(1,2,3,4);
    @Test void chaining(){  // ❗️ most of operations return Stream, so you can chain them
        s.map(x -> x + 1).filter(x -> x > 2).distinct().reduce(Integer::sum);
    }
    @Test void lazyEvaluation(){ // ❓ we want to execute fever operations if there is a chance.
        s.map(x -> {
            System.out.println("element: "+ x + ", is mapped ");
            return x;
        }).anyMatch(x -> x == 2 );
    }
    @Test void nonReusable(){   // ❗️stream is closed once we used its values.
        System.out.println(s.anyMatch(s -> s == 1));    // true
        System.out.println(s.noneMatch(s -> s == 1));
    }
}
