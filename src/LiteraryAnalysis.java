import java.util.*;

public class LiteraryAnalysis implements Analysis {
    private final String text;
    private final String label;
    private final Set<String> stopwords;
    private final Set<String> positiveWords;
    private final Set<String> negativeWords;
    private final TextProcessor processor = new TextProcessor();

    private int totalWords, totalSentences, totalParagraphs, totalCharacters, uniqueWordCount;
    private int vowelCount, consonantCount, mostFrequentCount;
    private double vocabularyRichness;
    private String mostFrequentWord = "", longestWord = "", sentiment = "Neutral";
    private List<Map.Entry<String, Integer>> topWords = List.of();

    public LiteraryAnalysis(String text, String label, Collection<String> stopwords,
                              Collection<String> positiveWords, Collection<String> negativeWords) {
        this.text = text;
        this.label = label;
        this.stopwords = new HashSet<>(stopwords);
        this.positiveWords = new HashSet<>(positiveWords);
        this.negativeWords = new HashSet<>(negativeWords);
    }

    @Override
    public void analyze() {
        ArrayList<String> allTokens = processor.tokenizeWords(text);
        ArrayList<String> filtered = new ArrayList<>();
        for (String token : allTokens) if (!stopwords.contains(token)) filtered.add(token);

        totalWords = filtered.size();
        totalSentences = processor.countSentences(text);
        totalParagraphs = processor.countParagraphs(text);
        Map<String, Integer> frequencies = new HashMap<>();

        for (String token : filtered) {
            totalCharacters += token.length();
            frequencies.merge(token, 1, Integer::sum);
            if (token.length() > longestWord.length()) longestWord = token;
        }

        uniqueWordCount = frequencies.size();
        vocabularyRichness = totalWords == 0 ? 0 : (double) uniqueWordCount / totalWords;

        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() > mostFrequentCount) {
                mostFrequentWord = entry.getKey();
                mostFrequentCount = entry.getValue();
            }
        }

        for (char c : text.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                if ("aeiou".indexOf(c) >= 0) vowelCount++;
                else consonantCount++;
            }
        }

        int positive = 0, negative = 0;
        for (String token : allTokens) {
            if (positiveWords.contains(token)) positive++;
            if (negativeWords.contains(token)) negative++;
        }
        if (positive > negative) sentiment = "Positive";
        else if (negative > positive) sentiment = "Negative";

        ArrayList<Map.Entry<String, Integer>> sorted = new ArrayList<>(frequencies.entrySet());
        sorted.sort((x, y) -> {
            int countComparison = Integer.compare(y.getValue(), x.getValue());
            return countComparison != 0 ? countComparison : x.getKey().compareTo(y.getKey());
        });
        topWords = sorted.subList(0, Math.min(20, sorted.size()));
    }

    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== ").append(label).append(" Analysis Report ===\n");
        report.append("Words (excluding stopwords): ").append(totalWords).append("\n");
        report.append("Sentences: ").append(totalSentences).append("\n");
        report.append("Paragraphs: ").append(totalParagraphs).append("\n");
        report.append("Characters in analyzed words: ").append(totalCharacters).append("\n");
        report.append("Unique words: ").append(uniqueWordCount).append("\n");
        report.append(String.format("Vocabulary richness: %.3f%n", vocabularyRichness));
        report.append("Most frequent word: ").append(mostFrequentWord)
              .append(" (").append(mostFrequentCount).append(")\n");
        report.append("Longest word: ").append(longestWord).append("\n");
        report.append("Vowels: ").append(vowelCount).append("\n");
        report.append("Consonants: ").append(consonantCount).append("\n");
        report.append("Sentiment: ").append(sentiment).append("\n");
        report.append("Top words:\n");
        for (Map.Entry<String, Integer> entry : topWords)
            report.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        return report.toString();
    }

    @Override
    public void displayResults() {
        System.out.println(generateReport());
    }
}
