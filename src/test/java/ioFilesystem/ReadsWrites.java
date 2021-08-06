package ioFilesystem;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ReadsWrites {
    @Test void readFile() throws IOException {  // Read line by line
        Path filePath = Paths.get("src/test/java/io/dataText.txt");
        BufferedReader bufferedReader = Files.newBufferedReader(filePath);
        String line;
        while ((line = bufferedReader.readLine()) != null) System.out.println(line);
    }
    @Test void writeText() throws IOException { // write what i want
        BufferedWriter bf = new BufferedWriter( new FileWriter("src/test/java/io/dataText.txt")); // overwrite
        bf.write("log1\n"); // append
        bf.write("log2\n"); //append
        bf.close();
    }
    @Test void saveObjects() throws IOException, ClassNotFoundException {
        var out = new ObjectOutputStream(new FileOutputStream("src/test/java/io/dataObject.txt"));
        var e1 = new Employee("henry", 5);
        var e2 = new Employee("riko", 17);
        out.writeObject(e1);
        out.writeObject(e2);
    }
    @Test void loadObjects() throws IOException, ClassNotFoundException {
        var in = new ObjectInputStream(new FileInputStream("src/test/java/io/dataObject.txt"));
        var readE1 =(Employee) in.readObject();
        var readE2 =(Employee) in.readObject();
    }
}
