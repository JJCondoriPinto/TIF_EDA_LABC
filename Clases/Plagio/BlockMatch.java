package Clases.Plagio;

public class BlockMatch {
    private int indexCoincidencia; // Posicion de palabra de última coincidencia
    private int indexStart; // Índice de inicio de la coincidencia en el texto original
    private int indexEnd;   // Índice de fin de la coincidencia en el texto original

    public BlockMatch(int indexCoincidencia, int indexStart, int indexEnd) {
        this.indexCoincidencia = indexCoincidencia;
        this.indexEnd = indexEnd;
        this.indexStart = indexStart;
    }

    public int getIndexCoincidencia() {
        return this.indexCoincidencia;
    }
    public int getIndexStart() {
        return this.indexStart;
    }
    public int getIndexEnd() {
        return this.indexEnd;
    }

    public void setIndexCoincidencia(int index) {
        this.indexCoincidencia = index;
    }
    public void setIndexStart(int index) {
        this.indexStart = index;
    }
    public void setIndexEnd(int index) {
        this.indexEnd = index;
    }
}
