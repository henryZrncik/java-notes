package optionals;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class Optionals {
    @Test void helloOptional(){
        Optional<Integer> optionalInt = Optional.of(120);
        Optional<Integer> emptyOne = Optional.empty();

        System.out.println(emptyOne.isEmpty());  // True
        System.out.println(optionalInt.isPresent());    // True
        System.out.println(optionalInt.get());  // 120

        var totalResult = optionalInt
                .map(a -> a + 5)
                .orElseGet(() -> {      // if null
                    System.out.println("salala");
                    return 4;
                });
        int presentOrDefault = emptyOne.orElse(15);
    }
    @Test void optionalUsedInClass(){
        Person p = new Person();
        System.out.println(p
                .getEmail()
                .map(String::toUpperCase)
                .orElse("he didn't provide"));
    }
}


