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

    public int size() {
        return this.size;
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

    public E remove(E data) throws ListEmptyException, NotFoundException{
        if (this.isEmpty())
            throw new ListEmptyException("Lista vacía");
        else {
            Node<E> aux = this.getRoot();
            if (!aux.getData().equals(data)) {
                while (aux != null) {
                    if (aux.hasNext() && aux.getNext().getData().equals(data))
                        break;
                    aux = aux.getNext();
                }
                if (aux == null) throw new NotFoundException("Elemento no encontrado");
                Node<E> item = aux.getNext();
                aux.setNext(item.getNext());
                this.size--;
                return item.getData();
            } else {
                E dataRem = aux.getData();
                this.root = aux.getNext();
                return dataRem;
            }
        }
    }

    public E getItem(E data) throws NotFoundException{
        Node<E> aux = this.getRoot();
        while (aux != null && !aux.getData().equals(data)) 
            aux = aux.getNext();
        if (aux == null) throw new NotFoundException("Elemento no encontrado");
        return aux.getData();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
