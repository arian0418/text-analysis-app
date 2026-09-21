import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextAnalysisApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FileHandler files = new FileHandler();
        ReportGenerator reports = new ReportGenerator();

        System.out.print("Enter the path for the first text file: ");
        String path1 = input.nextLine().trim();
        System.out.print("Enter the path for the second text file: ");
        String path2 = input.nextLine().trim();

        try {
            String text1 = files.readFile(path1);
            String text2 = files.readFile(path2);
            ArrayList<String> stopwords = loadOptional(files, "stopwords.txt");
            ArrayList<String> positive = loadOptional(files, "positive.txt");
            ArrayList<String> negative = loadOptional(files, "negative.txt");

            LiteraryAnalysis first = new LiteraryAnalysis(text1, "Text 1", stopwords, positive, negative);
            LiteraryAnalysis second = new LiteraryAnalysis(text2, "Text 2", stopwords, positive, negative);
            first.analyze();
            second.analyze();

            System.out.println();
            first.displayResults();
            second.displayResults();

            reports.saveReport(first.generateReport(), "analysis_report_1.txt");
            reports.saveReport(second.generateReport(), "analysis_report_2.txt");

            double score = new SimilarityCalculator().computeSimilarity(text1, text2);
            System.out.printf("Cosine similarity: %.4f%n", score);
            System.out.println(score >= 0.7 ? "The texts are similar." : "The texts are different.");
            System.out.println("Analysis completed and reports saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            input.close();
        }
    }

    private static ArrayList<String> loadOptional(FileHandler files, String path) {
        try {
            return files.readWordList(path);
        } catch (FileNotFoundException e) {
            System.out.println("Warning: " + path + " not found; continuing without it.");
            return new ArrayList<>();
        }
    }
}
