package optionals;

import java.util.Optional;

class Person {
    private String email;

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
