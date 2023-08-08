package Clases.Trie;

public class Trie {
    private TrieNode root;
    private int currentIndex; // Índice actual para asignar a las palabras
    public String text;

    public Trie() {
        root = new TrieNode();
        currentIndex = 0;
        text = "";
    }

    public Trie(String text) {
        this();
        insertText(text);
    }

    public void insertText(String text) {
        String[] words = text.split("\\s+");

        for (String word : words) {
            insert(word.toLowerCase(), currentIndex);
            currentIndex++;
        }
    }

    public void insert(String word, int index) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                node.children[charIndex] = new TrieNode();
            }
            node = node.children[charIndex];
        }
        node.isEndOfWord = true;
        node.wordIndex = index;
    }

    public boolean search(String word, int startIndex) {
        if (startIndex < 0 || startIndex >= text.length()) {
            return false; // startIndex fuera de los límites de la cadena
        }
    
        TrieNode node = root;
        int endIndex = Math.min(startIndex + word.length(), text.length());
    
        for (int i = startIndex; i < endIndex; i++) {
            char c = text.charAt(i); // Obtener el carácter en la posición i del texto
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return false;
            }
            node = node.children[charIndex];
        }
        return node.isEndOfWord;
    }    
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return false;
            }
            node = node.children[charIndex];
        }
        return true;
    }
}
