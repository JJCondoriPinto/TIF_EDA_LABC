package Clases.Trie;

class TrieNode {
    public static final int ALPHABET_SIZE = 27;
    TrieNode[] children;
    boolean isEndOfWord; 
    int wordIndex;  // Índice de posición de la palabra
    
    public TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];  // Tamaño de 27 para las 26 letras del abecedario más un carácter especial
        isEndOfWord = false;
        wordIndex = -1;  // Valor por defecto para indicar que no se ha asignado un índice
    }
}
