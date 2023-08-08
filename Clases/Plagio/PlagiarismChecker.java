package Clases.Plagio;

import Clases.Listas.LinkedList;
import Clases.Trie.Trie;
import Controladores.FuncionalidadTrie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                Trie trie = new Trie();
                String regexPattern1 = "(?i)titulo:\\s*(.+)"; 
                String regexPattern2 = "(?i)autor:\\s*(.+)"; 
                Pattern pattern1 = Pattern.compile(regexPattern1);
                Pattern pattern2 = Pattern.compile(regexPattern2);
                String titulo = "", autor = "";
                while(sc.hasNext()) {
                    String word = sc.nextLine();
                    if (titulo.equals("")) {
                        Matcher matcher = pattern1.matcher(word);
                        while (matcher.find())
                            titulo = matcher.group(1);
                        continue;
                    }
                    if (autor.equals("")) {
                        Matcher matcher = pattern2.matcher(word);
                        while (matcher.find()) 
                            autor = matcher.group(1);
                        continue;
                    }
                    trie.insertText(word);
                }
                FileDB fileDb = new FileDB(trie, titulo, autor, FuncionalidadTrie.countId++);
                this.tries.add(fileDb);
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
        System.out.println(Arrays.toString(words));
        // for (int i = 0; i < tries.size(); i++) {
        //     FileDB fileDB = tries.get(i);
        //     Trie trie = fileDB.getFile();
            
        //     for (int j = 0; j < words.length; j++) {
        //         String word = words[j];
        //         if (trie.search(word, j)) {
        //             int startIndex = j;
        //             int endIndex = j + word.length() - 1;
        //             resultChecker.addMatch(startIndex, endIndex);
        //         }
        //     }
        // }
        return resultChecker;
    }
}
