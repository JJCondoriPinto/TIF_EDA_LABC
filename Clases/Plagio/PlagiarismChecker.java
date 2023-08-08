package Clases.Plagio;

import Clases.Listas.LinkedList;
import Clases.Listas.Node;
import Clases.Trie.Trie;
import Clases.Trie.TrieNode;
import Controladores.FuncionalidadTrie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlagiarismChecker {

    public static final int UMBRAL_SEPARACION_PALABRAS = 3;
    public static final float UMBRAL_PORCENTAJE_COINCIDENCIA = 0.40f;
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
        ResultChecker resultChecker = new ResultChecker(text, this.tries.size());
        String[] words = text.split("\\s+");
        boolean[] coincidencias = new boolean[words.length];
        int lengthWord = 0;
        for(int i = 0; i < words.length; i++) {
            lengthWord += words[i].length()+1;
            Node<FileDB> aux = this.tries.getRoot();
            int index = 0;
            while(aux != null) { // Recorremos todos los tries en busqueda de coincidencias
                FileDB file = aux.getData();
                Trie trie = file.getFile();
                TrieNode match = trie.search(words[i]);
                if (match != null) { // Encontró la palabra
                    resultChecker.addMatch(index, lengthWord - words[i].length()-1, lengthWord, this.tries.get(index));
                    int indexCurrent = resultChecker.getMatch(index).getIndexCoincidencia();
                    int indexWord = match.getIndexFirst(indexCurrent); // Posicion en texto de trie
                    // Coincidencia valida
                    if (indexCurrent == -1 || (indexWord - indexCurrent <= UMBRAL_SEPARACION_PALABRAS && indexWord - indexCurrent > 0)) {
                        resultChecker.coincidente(index, indexWord); // +1 coincidencia en el file
                        resultChecker.getMatch(index).setEndIndex(lengthWord);
                        coincidencias[i] = true;
                    }
                    // Si es una coincidencia no valida se separa otro bloque para nuevas coincidencias
                    else {
                        resultChecker.getMatch(index).addBlock(lengthWord-words[i].length()-1, indexCurrent);
                        continue;
                    }
                }
                aux = aux.getNext();
                index++;
            }
        }
        int coincidenciasTotal = 0;
        for (boolean bool : coincidencias)
            coincidenciasTotal += bool ? 1 : 0;

        if (coincidenciasTotal*1.0 / words.length < UMBRAL_PORCENTAJE_COINCIDENCIA)
            return null;
        return resultChecker;
    }
}
