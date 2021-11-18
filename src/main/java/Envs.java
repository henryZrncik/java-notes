import java.util.Map;

public class Envs {
    public static void main(String[] args) {
        Map<String, String> enviorntmentVars  = System.getenv();
        enviorntmentVars.entrySet().forEach(System.out::println);
    }
}
