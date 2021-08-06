package futures;
import java.util.function.Supplier;
import static java.lang.Thread.sleep;

class MySupplier<Integer> implements Supplier<java.lang.Integer> {
    public int timeToSleep;
    public MySupplier(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    @Override
    public java.lang.Integer get() {
        System.out.println("task: gonna sleep " + timeToSleep + " starts ");
        try { sleep(timeToSleep);}
        catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("task: gonna sleep " + timeToSleep + " finished ");
        return timeToSleep;
    }
}
