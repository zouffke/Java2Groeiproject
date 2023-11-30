package be.kdg.kollections.lists;


import be.kdg.kollections.Kollections;

public class LinkedList<E> implements List<E> {
    //region vars
    static class Node<V> {
        V value;
        Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    private Node<E> root;
    private int size;
    //endregion

    //region constructors
    public LinkedList() {
    }
    //endregion

    //region Override
    //region List interface
    //region add
    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        if (index == 0) {
            root = new Node<>(element);
            root.next = this.root;
        } else {
            Node<E> node = this.root;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }

            Node<E> newNode = new Node<>(element);
            node.next = newNode;
            newNode.next = node;
        }
        this.size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }
    //endregion

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        Node<E> node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.value = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        if (index == 0) {
            E oldElement = root.value;
            root = root.next;
            size--;
            return oldElement;
        } else {
            Node<E> beforeNode = root;
            for (int i = 1; i < index; i++) {
                beforeNode = beforeNode.next;
            }
            E oldElement = beforeNode.next.value;
            beforeNode.next = beforeNode.next.next;
            size--;
            return oldElement;
        }
    }

    @Override
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        Node<E> node = this.root;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.value;
    }

    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
    }

    //region Collection interface

    @Override
    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index == -1) return false;
        return this.remove(index).equals(element);
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) != -1;
    }


    //endregion

    //endregion
    //endregion
}
