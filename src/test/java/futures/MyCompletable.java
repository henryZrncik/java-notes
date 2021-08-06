package futures;

import java.util.concurrent.Callable;

class MyCompletable<Integer> implements Callable<java.lang.Integer> {
    public int timeToSleep;
    public MyCompletable(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    @Override
    public java.lang.Integer call() throws Exception {
        System.out.println("task: gonna sleep " + timeToSleep + " starts ");
        Thread.sleep(timeToSleep);
        System.out.println("task: gonna sleep " + timeToSleep + " finished ");
        return timeToSleep;
    }
}