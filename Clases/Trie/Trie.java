package Clases.Trie;

public class Trie {
    private TrieNode root;
    private int currentIndex; // Índice actual para asignar a las palabras

    public Trie() {
        root = new TrieNode();
        currentIndex = 0;
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

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
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
