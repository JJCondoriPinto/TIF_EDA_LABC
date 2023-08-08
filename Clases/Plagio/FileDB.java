package Clases.Plagio;

import Clases.Trie.Trie;

public class FileDB {
    private Trie file;
    private final int ID;
    private String titulo;
    private String autor;
    private String descripcion;

    public FileDB(int ID) {
        this.ID = ID;
    }

    public FileDB(Trie file, String titulo, String autor, String descripcion, int ID) {
        this.file = file;
        this.titulo = titulo;
        this.autor = autor;
        this.descripcion = descripcion;
        this.ID = ID;
    }

    public FileDB(Trie file, String titulo, String autor, int ID) {
        this(file, titulo, autor, "", ID);
    }

    public Trie getFile() {
        return this.file;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public int getId() {
        return this.ID;
    }

    public void setFile(Trie file) {
        this.file = file;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object file) {
        if (file instanceof FileDB) {
            return this.ID == ((FileDB) file).getId();
        }
        return false;
    }
}

