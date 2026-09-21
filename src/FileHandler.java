import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public String readFile(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) content.append(scanner.nextLine()).append("\n");
        }
        return content.toString();
    }

    public ArrayList<String> readWordList(String filePath) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim().toLowerCase();
                if (!line.isEmpty()) words.add(line);
            }
        }
        return words;
    }
}
