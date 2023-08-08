package Controladores;

import Clases.Exceptions.ListEmptyException;
import Clases.Exceptions.NotFoundException;
import Clases.Listas.LinkedList;
import Clases.Plagio.FileDB;
import Clases.Plagio.PlagiarismChecker;
import Clases.Plagio.ResultChecker;
import Clases.Trie.Trie;

public class FuncionalidadTrie { // Define los tries 
    
    private PlagiarismChecker checker;
    private LinkedList<FileDB> listFiles;
    private int countId;

    public FuncionalidadTrie() {
        this.listFiles = new LinkedList<FileDB>(null);
        this.checker = new PlagiarismChecker(this.listFiles);
        this.countId = 0;
    }

    public int addFileText(String text, String titulo, String autor, String descripcion) {
        FileDB file = new FileDB(new Trie(text), titulo, autor, descripcion, this.countId);
        this.listFiles.add(file);
        return this.countId++;
    }

    public ResultChecker verifyPlagiarism(String text) {
        return this.checker.verifyPlagiarism(text);
    }

    public boolean loadFiles(String[] paths) {
        return this.checker.loadFiles(paths);
    }

    public FileDB getFile(int id) {
        try {
            System.out.println(id);
            FileDB file = this.listFiles.getItem(new FileDB(id));
            return file;
        } catch (NotFoundException err) {
            return null;
        }
    }

    public FileDB removeFile(int id) {
        try {
            FileDB rem = this.listFiles.remove(new FileDB(id));
            return rem;
        } catch (NotFoundException | ListEmptyException e) {
            return null;
        }
    }


}
