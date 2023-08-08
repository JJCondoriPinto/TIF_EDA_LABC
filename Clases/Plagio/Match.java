package Clases.Plagio;

import Clases.Listas.LinkedList;

public class Match {
    private FileDB fileMatching;
    private int numCouncidencias;
    private LinkedList<BlockMatch> matches;

    public Match(int startIndex, int endIndex, FileDB file) {
        this.numCouncidencias = 0;
        this.matches = new LinkedList<>(null);
        this.matches.add(new BlockMatch(-1, startIndex, endIndex));
        this.fileMatching = file;
    }

    public void setFile(FileDB file) {
        this.fileMatching = file;
    }

    public void addBlock(int startIndex, int endIndex) {
        this.matches.add(new BlockMatch(-1, startIndex, endIndex));
    }

    public LinkedList<BlockMatch> getListBlocks() {
        return this.matches;
    }

    public BlockMatch getLastBlock() {
        return this.matches.getLast();
    }

    public FileDB getFile() {
        return this.fileMatching;
    }

    public int getIndexCoincidencia() {
        return this.matches.getLast().getIndexCoincidencia();
    }

    public void setIndexCoincidencia(int index) {
        this.matches.getLast().setIndexCoincidencia(index);
    }

    public void addCoincidencia() {
        this.numCouncidencias++;
    }

    public int getCoincidencias() {
        return this.numCouncidencias;
    }

    public int getStartIndex() {
        return this.matches.getLast().getIndexStart();
    }

    public int getEndIndex() {
        return this.matches.getLast().getIndexEnd();
    }

    public void setStartIndex(int index) {
        this.matches.getLast().setIndexStart(index);
    }

    public void setEndIndex(int index) {
        this.matches.getLast().setIndexEnd(index);
    }
}

