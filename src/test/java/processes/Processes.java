package processes;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Processes {
    ProcessBuilder processBuilderLS  = new ProcessBuilder("ls", "-al");
    ProcessBuilder processBuilderWC  = new ProcessBuilder("wc", "--c");

    @Test void redirectSTDOUTtoConsoleAndMergeSTDERR() throws IOException {
        processBuilderLS.redirectErrorStream(true); // have both STDIN and STDERR in one debugSTDOUTSTDERRredirect.
        // redirect STDOUT and STDERR of command to your STDIN, in that case you won't catch anything in inputStram.
        processBuilderLS.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process p = processBuilderLS.start();
    }
    @Test void getSTDOUT() throws IOException {
        Process p = processBuilderLS.start();
        // OutputStream for JVM is input stream for process, and process output is your (JVM) input.
        // so you sort of want InputStream from process when you want its "output"
        System.out.println(new String(p.getInputStream().readAllBytes()));
    }
    @Test void executeListOfProcessesAsPipe() throws IOException { // output of one is input of another
        List<Process> myProcesses = ProcessBuilder.startPipeline(
            List.of(processBuilderLS, processBuilderWC
        ));
        Process lastProcess = myProcesses.get(myProcesses.size() - 1);
        System.out.println(new String(lastProcess.getInputStream().readAllBytes()));
    }
    @Test void getReturnedValue() throws IOException, InterruptedException {
        Process p = processBuilderLS.start();
        int result = 5;
        // true if not time out
        if (p.waitFor(2, TimeUnit.SECONDS))  result = p.exitValue();
        else p.destroy(); // SIGTERM
        System.out.println(result);
    }



}
