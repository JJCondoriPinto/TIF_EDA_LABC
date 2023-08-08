package Clases.Plagio;
import Clases.Listas.*;
import Clases.Trie.Trie;

public class ResultChecker {
    private String originalText;
    private LinkedList<Match> matches;

    public ResultChecker(String originalText) {
        this.originalText = originalText;
        matches = new LinkedList<>(null);
    }

    public void addMatch(int startIndex, int endIndex, Trie trie) {
    Match match = new Match(startIndex, endIndex, trie);
    matches.add(match);
}

    public String getOriginalText() {
        return originalText;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }
}

