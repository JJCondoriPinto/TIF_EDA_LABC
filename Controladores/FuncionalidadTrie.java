package Controladores;

import java.util.ArrayList;

import Clases.Exceptions.ListEmptyException;
import Clases.Exceptions.NotFoundException;
import Clases.Listas.LinkedList;
import Clases.Listas.Node;
import Clases.Plagio.FileDB;
import Clases.Plagio.PlagiarismChecker;
import Clases.Plagio.ResultChecker;
import Clases.Trie.Trie;

public class FuncionalidadTrie { // Define los tries 
    
    private PlagiarismChecker checker;
    private LinkedList<FileDB> listFiles;
    public static int countId = 0;

    public FuncionalidadTrie() {
        this.listFiles = new LinkedList<FileDB>(null);
        this.checker = new PlagiarismChecker(this.listFiles);
    }

    public ArrayList<FileDB> getAllFiles() {
        if (!this.listFiles.isEmpty()) {
            ArrayList<FileDB> files = new ArrayList<>();
            Node<FileDB> aux = this.listFiles.getRoot();
            while(aux != null) {
                files.add(aux.getData());
                aux = aux.getNext();
            }
            return files;
        }
        return null;
    }

    public int addFileText(String text, String titulo, String autor, String descripcion) {
        FileDB file = new FileDB(new Trie(text), titulo, autor, descripcion, countId);
        this.listFiles.add(file);
        return countId++;
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
