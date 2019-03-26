package ee.auto24.parsing.objects;

import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String message) throws IOException {
        String currentDateForFileName = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        PrintWriter out = new PrintWriter(new FileWriter("output_" + currentDateForFileName + ".csv", true), true);
        out.write(message);
        out.close();
    }

    public static void logRow(Elements makeRow) {
        makeRow.forEach(Element -> {
            String makeUrl = Element.attr("abs:href");
            CarFromDocument carFromDocument = new CarFromDocument(makeUrl);
            try {
                Logger.log(carFromDocument.getTextToWrite());
                Logger.log(System.getProperty("line.separator"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
