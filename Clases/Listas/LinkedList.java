package Clases.Listas;

import Clases.Exceptions.ListEmptyException;
import Clases.Exceptions.NotFoundException;

public class LinkedList<E> {
    private Node<E> root;
    private int size;

    public LinkedList(Node<E> root) {
        this.root = root;
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public void add(E data) {
        if (this.isEmpty()) {
            this.setRoot(new Node<E>(data));
        } else {
            Node<E> aux = this.getRoot();
            while (aux.hasNext()) 
                aux = aux.getNext();
            aux.setNext(new Node<E>(data));
        }
        this.size++;
    }

    public Node<E> remove(E data) throws ListEmptyException, NotFoundException{
        if (this.isEmpty())
            throw new ListEmptyException("Lista vacía");
        else {
            Node<E> aux = this.getRoot();
            while (aux != null && !aux.getNext().equals(data)) 
                aux = aux.getNext();
            if (aux == null) throw new NotFoundException("Elemento no encontrado");
            Node<E> item = aux.getNext();
            aux.setNext(item.getNext());
            this.size--;
            return item;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
