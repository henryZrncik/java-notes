package streams;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Operations {
    List<Integer> myList = List.of( 20,5,20,1,8,3);
    @Test void map_filter() {
        myList.stream()
                .filter(x -> x < 4 ) // Stream {1,3}
                .map(x -> x * 10) // Stream {10, 30}
                .forEach(System.out::println);
    }
    @Test void skip_distinct(){
        myList.stream()
                .distinct() // {20,5,1,8,3}
                .skip(3) // new Stream{8,3}
                .forEach(System.out::println);  // void
    }
    @Test void flatMap(){
        String[][] array2D = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        String[] array1D = Stream.of(array2D)    // Stream<String[]>
            .flatMap(Stream::of) // Stream<String>
            .toArray(String[]::new);    // ["a", "b", "c" ...]
    }
    @Test void sort_first(){
        myList.stream()
                .sorted()   // new Stream {1,2,3,5,8}
                .findFirst()    // Optional<Integer> // Optional<1>
                .ifPresent((a) -> System.out.println(a));
    }
    @Test void  reductions(){
        int result = Arrays.asList(1, 2, 3).stream() .reduce(0, (acc, element) -> acc + element);  // own reduction
        System.out.println(result);
        //-----------//
        var r = Arrays.asList(1, 2, 3).stream().reduce(Integer::sum); // predefined
        System.out.println(r.orElse(0));
    }
    @Test void  findAny(){
        List<Integer> inArray = Arrays.asList(1, 2, 3);
        var result = inArray.stream().anyMatch(e -> e ==2 ); // boolean
    }
    @Test void Collect(){
        var rAsList = List.of(1,2,3,2).stream().map(x -> x * 10).collect(Collectors.toList());    // size = 4
        var rAsSet = List.of(1,2,3,2).stream().map(x -> x * 10).collect(Collectors.toSet()); // size = 3
    }

    @Test void Parallel(){
        var x = Arrays.asList("a1", "a2", "b1", "c2", "c1", "ccc", "ccccccc")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",  s, Thread.currentThread().getName());
                    return true;
                });
        System.out.println(x.toArray()); // this line just to force lazy evaluation.
    }





}


