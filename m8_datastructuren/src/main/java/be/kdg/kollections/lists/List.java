package be.kdg.kollections.lists;

import be.kdg.kollections.Collection;

public interface List<E> extends Collection<E> {
    void add(int index, E element);

    void add(E element);

    void set(int index, E element);

    int size();

    E remove(int index);

    E get(int index);

    int indexOf(E element);
}
