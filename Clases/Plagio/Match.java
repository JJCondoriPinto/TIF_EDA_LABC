package Clases.Plagio;

public class Match {
    private FileDB fileMatching;
    private int numCouncidencias;
    private int indexCoincidencia; // Posicion de palabra de última coincidencia
    private int startIndex; // Índice de inicio de la coincidencia en el texto original
    private int endIndex;   // Índice de fin de la coincidencia en el texto original

    public Match(int startIndex, int endIndex, FileDB file) {
        this.indexCoincidencia = -1;
        this.numCouncidencias = 0;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.fileMatching = file;
    }

    public void setFile(FileDB file) {
        this.fileMatching = file;
    }

    public FileDB getFile() {
        return this.fileMatching;
    }

    public int getIndexCoincidencia() {
        return this.indexCoincidencia;
    }

    public void setIndexCoincidencia(int index) {
        this.indexCoincidencia = index;
    }

    public void addCoincidencia() {
        this.numCouncidencias++;
    }

    public int getCoincidencias() {
        return this.numCouncidencias;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setStartIndex(int index) {
        this.startIndex = index;
    }

    public void setEndIndex(int index) {
        this.endIndex = index;
    }
}

