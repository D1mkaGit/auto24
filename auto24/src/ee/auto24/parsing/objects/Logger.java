package ee.auto24.parsing.objects;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    public static void log(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output.csv", true), true);
        out.write(message);
        out.close();
    }
}
