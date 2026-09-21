import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ReportGenerator {
    public void saveReport(String report, String filePath) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.print(report);
        }
    }
}
