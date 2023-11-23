package be.kdg.kollections;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void expand() {
        Object[] temp = new Object[this.size + INITIAL_CAPACITY];

        if (this.size >= 0) System.arraycopy(this.elements, 0, temp, 0, this.size);

        this.elements = temp;
    }

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

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
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
}
