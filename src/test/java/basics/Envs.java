package basics;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class Envs {
    @Test void getEnvs(){
        Map<String, String> enviorntmentVars  = System.getenv();
        enviorntmentVars.entrySet().forEach(System.out::println);
    }
}