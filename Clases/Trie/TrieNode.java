package Clases.Trie;

import Clases.Listas.PriorityQueue;

public class TrieNode {
    public static final int ALPHABET_SIZE = 27;
    TrieNode[] children;
    boolean isEndOfWord; 
    private PriorityQueue<Integer> wordIndex;  // Índice de posición de la palabra
    
    public TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];  // Tamaño de 27 para las 26 letras del abecedario más un carácter especial
        isEndOfWord = false;
        wordIndex = new PriorityQueue<>();  // Valor por defecto para indicar que no se ha asignado un índice
    }

    public PriorityQueue<Integer> getIndex() {
        return this.wordIndex;
    }

    public void setIndex(int index) {
        this.wordIndex.insert(index);
    }

    public int getIndexFirst(int indexCurrent) {
        PriorityQueue<Integer> aux = this.wordIndex.copy();
        int auxInteger = aux.delete();
        while(!aux.isEmpty() && auxInteger < indexCurrent) {
            auxInteger = aux.delete();
        }
        return auxInteger;
    }
}
