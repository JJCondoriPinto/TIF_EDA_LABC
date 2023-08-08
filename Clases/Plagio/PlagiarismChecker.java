package Clases.Plagio;

import Clases.Listas.LinkedList;
import Clases.Trie.Trie;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlagiarismChecker {

    private LinkedList<FileDB> tries;

    public PlagiarismChecker(LinkedList<FileDB> tries) {
        this.tries = tries;
    }

    public boolean loadFiles(String[] paths) {
        try {
            for(String path : paths) {
                FileInputStream file = new FileInputStream(path);
                Scanner sc = new Scanner(file);
                while(sc.hasNext()) {
                    String word = sc.next();
                    // Añade las palabras en el trie
                    System.out.println(word);
                }
                sc.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ResultChecker verifyPlagiarism(String text) {
        return null;
    }



}
