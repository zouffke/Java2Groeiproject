package be.kdg.kollections.lists;

import be.kdg.kollections.Kollections;

public class ArrayList<E> implements List<E> {
    //region vars
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;
    //endregion

    //region constructors

    public ArrayList(int size) {
        elements = new Object[size];
        size = 0;
    }

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }
    //endregion

    //region ownFunctions
    private void expand() {
        Object[] temp = new Object[this.size + INITIAL_CAPACITY];

        if (this.size >= 0) System.arraycopy(this.elements, 0, temp, 0, this.size);

        this.elements = temp;
    }
    //endregion

    //region Overrides
    //region List Interface
    //region add
    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        if (this.size == elements.length) {
            expand();
        }

        Object[] temp = new Object[this.elements.length];

        for (int i = 0; i < this.size + 1; i++) {
            if (i < index) {
                temp[i] = this.elements[i];
            } else if (i == index) {
                temp[i] = element;
            } else {
                temp[i] = this.elements[i - 1];
            }
        }

        size++;

        this.elements = temp;
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
        elements[index] = element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E oldValue = (E) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        return (E) this.elements[index];
    }

    @Override
    public int indexOf(E element) {
        return Kollections.lineairSearch(this, element);
    }
    //endregion
    //region Collection Interface

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
}
