package Clases.Trie;

class Trie<T> {
    private TrieNode<T> root;

    public Trie() {
        root = new TrieNode<>();
    }

    public void insert(T[] word) {
        TrieNode<T> node = root;
        for (T character : word) {
            node.children.putIfAbsent(character, new TrieNode<>());
            node = node.children.get(character);
        }
        node.isEndOfWord = true;
    }

    public boolean search(T[] word) {
        TrieNode<T> node = root;
        for (T character : word) {
            if (!node.children.containsKey(character)) {
                return false;
            }
            node = node.children.get(character);
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(T[] prefix) {
        TrieNode<T> node = root;
        for (T character : prefix) {
            if (!node.children.containsKey(character)) {
                return false;
            }
            node = node.children.get(character);
        }
        return true;
    }
}

