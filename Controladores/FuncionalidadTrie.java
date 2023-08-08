package Controladores;

import Clases.Listas.LinkedList;
import Clases.Plagio.PlagiarismChecker;
import Clases.Plagio.ResultChecker;
import Clases.Trie.Trie;

public class FuncionalidadTrie { // Define los tries 
    
    private PlagiarismChecker checker;
    private LinkedList<Trie> listFiles;

    public FuncionalidadTrie() {
        this.listFiles = new LinkedList<>(null);
        this.checker = new PlagiarismChecker(this.listFiles);
    }

    public void addFileText(String text) {
        this.listFiles.add(new Trie(text));
    }

    public ResultChecker verifyPlagiarism(String text) {
        return this.checker.verifyPlagiarism(text);
    }

    public boolean loadFiles(String[] paths) {
        return this.checker.loadFiles(paths);
    }


}
