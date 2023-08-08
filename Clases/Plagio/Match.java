package Clases.Plagio;

import Clases.Trie.Trie;

public class Match {
    private int startIndex; // Índice de inicio de la coincidencia en el texto original
    private int endIndex;   // Índice de fin de la coincidencia en el texto original
    private Trie sourceTrie;

    public Match(int startIndex, int endIndex, Trie sourceTrie) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.sourceTrie = sourceTrie;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
    public Trie getSourceTrie() {
        return sourceTrie;
    }
}

