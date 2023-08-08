package Clases.Plagio;

public class ResultChecker {
    private String originalText;
    private Match[] matches;

    public ResultChecker(String originalText, int longFiles) {
        this.originalText = originalText;
        matches = new Match[longFiles];
    }

    public void addMatch(int index, int startIndex, int endIndex, FileDB file) {
        if (this.matches[index] == null)
            this.matches[index] = new Match(startIndex, endIndex, file);
    }

    public Match getMatch(int index) {
        return this.matches[index];
    }

    public void coincidente(int posFile, int indexWord) {
        this.matches[posFile].addCoincidencia();
        this.matches[posFile].setIndexCoincidencia(indexWord);
    }

    public String getOriginalText() {
        return originalText;
    }

    public Match[] getMatches() {
        return this.matches;
    }
}

