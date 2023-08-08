package Clases.Trie;

public class Trie {
    private TrieNode root;
    private int currentIndex; // Índice actual para asignar a las palabras

    public Trie() {
        root = new TrieNode();
        currentIndex = 0;
    }

    public Trie(String text) {
        this();
        insertText(text);
    }

    public void insertText(String text) {
        String[] words = text.split("\\s+");

        for (String word : words) {
            word = word.replaceAll("\\p{M}", "");
            word = word.replaceAll("[^a-zA-Z]", "");
            insert(word.toLowerCase(), currentIndex++);
        }
    }

    public void insert(String word) {
        word = word.replaceAll("\\p{M}", "");
        word = word.replaceAll("[^a-zA-Z]", "");
        this.insert(word, ++currentIndex);
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
        node.setIndex(index);
    }

    public TrieNode search(String word) {
        TrieNode node = root;
        word = word.replaceAll("\\p{M}", "");
        word = word.replaceAll("[^a-zA-Z]", "");
        for (int i = 0; i < word.length(); i++) {
            char c = word.toLowerCase().charAt(i); // Obtener el carácter en la posición i del texto
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return null;
            }
            node = node.children[charIndex];
        }
        return node.isEndOfWord ? node : null;
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