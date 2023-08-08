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
        ResultChecker resultChecker = new ResultChecker(text);
        String[] words = text.split("\\s+");
        
        for (int i = 0; i < tries.size(); i++) {
            FileDB fileDB = tries.get(i);
            Trie trie = fileDB.getFile();
            int currentIndex = 0;
                
            for (String word : words) {
                if (trie.search(word, currentIndex)) {
                    int startIndex = currentIndex;
                    int endIndex = currentIndex + word.length() - 1;
                    resultChecker.addMatch(startIndex, endIndex);
                }
                currentIndex += word.length() + 1; // Considerar el espacio entre palabras
            }
        }

        return resultChecker;
    }
}
