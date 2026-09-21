import java.util.ArrayList;

public class SimilarityCalculator {
    private final TextProcessor processor = new TextProcessor();

    public double computeSimilarity(String text1, String text2) {
        ArrayList<String> words1 = processor.tokenizeWords(text1);
        ArrayList<String> words2 = processor.tokenizeWords(text2);
        ArrayList<String> vocabulary = processor.buildVocabulary(words1, words2);
        return processor.cosineSimilarity(
                processor.buildVector(vocabulary, words1),
                processor.buildVector(vocabulary, words2));
    }
}
