package Clases.Trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode<T> {
    Map<T, TrieNode<T>> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}
