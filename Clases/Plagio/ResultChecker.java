package Clases.Plagio;
import Clases.Listas.*;

public class ResultChecker {
    private String originalText;
    private LinkedList<Match> matches;

    public ResultChecker(String originalText) {
        this.originalText = originalText;
        matches = new LinkedList<>(null);
    }

    public void addMatch(int startIndex, int endIndex) {
        matches.add(new Match(startIndex, endIndex));
    }

    public String getOriginalText() {
        return originalText;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }
}

