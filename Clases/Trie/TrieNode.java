package Clases.Trie;

class TrieNode {
    TrieNode[] children;  // Un arreglo de nodos hijos
    boolean isEndOfWord;  // Indica si este nodo representa el final de una palabra
    
    public TrieNode() {
        children = new TrieNode[27];  // Tamaño de 27 para las 26 letras del abecedario más un carácter especial
        isEndOfWord = false;
    }
}