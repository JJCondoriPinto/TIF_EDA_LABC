package Clases.Plagio;

public class Match {
    private int startIndex; // Índice de inicio de la coincidencia en el texto original
    private int endIndex;   // Índice de fin de la coincidencia en el texto original

    public Match(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}

