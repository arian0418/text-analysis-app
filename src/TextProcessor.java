import java.util.ArrayList;
import java.util.LinkedHashSet;

public class TextProcessor {
    public ArrayList<String> tokenizeWords(String text) {
        ArrayList<String> tokens = new ArrayList<>();
        for (String raw : text.split("\\s+")) {
            String cleaned = raw.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!cleaned.isEmpty()) tokens.add(cleaned);
        }
        return tokens;
    }

    public int countSentences(String text) {
        int count = 0;
        for (char c : text.toCharArray()) if (c == '.' || c == '!' || c == '?') count++;
        return count;
    }

    public int countParagraphs(String text) {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) return 0;
        return trimmed.split("(\\r?\\n){2,}").length;
    }

    public ArrayList<String> buildVocabulary(ArrayList<String> a, ArrayList<String> b) {
        LinkedHashSet<String> set = new LinkedHashSet<>(a);
        set.addAll(b);
        return new ArrayList<>(set);
    }

    public int[] buildVector(ArrayList<String> vocabulary, ArrayList<String> tokens) {
        int[] vector = new int[vocabulary.size()];
        for (String token : tokens) {
            int index = vocabulary.indexOf(token);
            if (index >= 0) vector[index]++;
        }
        return vector;
    }

    public double cosineSimilarity(int[] a, int[] b) {
        double dot = 0, magA = 0, magB = 0;
        for (int i = 0; i < a.length; i++) {
            dot += (double) a[i] * b[i];
            magA += (double) a[i] * a[i];
            magB += (double) b[i] * b[i];
        }
        if (magA == 0 || magB == 0) return 0;
        return dot / (Math.sqrt(magA) * Math.sqrt(magB));
    }
}
