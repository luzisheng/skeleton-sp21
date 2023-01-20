package deque;

import net.sf.saxon.om.Item;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {

    private T[] items;
    // size of ArrayDeque
    private int size;
    // size of Array
    private int aSize;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        aSize = 8;
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = aSize - 1;
        nextLast = 0;
    }

    // return the first item in array according to nextFirst
    private int currentFirst() {
        if (nextFirst + 1 == aSize) { return 0; }
        return nextFirst + 1;
    }

    // return the last item in array according to nextLast
    private int currentLast() {
        if (nextLast - 1 < 0) { return aSize - 1; }
        return nextLast - 1;
    }

    private void resize() {
        // to expand
        if (size == aSize) {
            T[] newItems = (T[]) new Object[aSize * 2];
            int current = currentFirst();
            int newIndex = 0;

            while (newIndex < size) {
                newItems[newIndex] = items[current];
                if (current + 1 == size) { current = 0; }
                else { current += 1; }

                newIndex = newIndex + 1;
            }

            items = newItems;
            aSize = aSize * 2;
            nextFirst = aSize - 1;
            nextLast = size;
            return ;
        }

        // to shrink
        if (aSize >= 16 && size * 4 < aSize) {
            T[] newItems = (T[]) new Object[size * 2];
            int current = currentFirst();
            int newIndex = 0;

            while (newIndex < size) {
                newItems[newIndex] = items[current];
                if (current + 1 == aSize) { current = 0; }
                else { current += 1; }

                newIndex = newIndex + 1;
            }

            items = newItems;
            aSize = size * 2;
            nextFirst = aSize - 1;
            nextLast = size;
        }
    }

    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = nextFirst - 1;
        if (nextFirst < 0) {
            nextFirst = aSize - 1;
        }
        size = size + 1;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = nextLast + 1;
        if (nextLast == aSize) {
            nextLast = 0;
        }
        size = size + 1;
    }

    public boolean isEmpty() { return (size == 0); }

    public int size() { return size; }

    public void printDeque() {
        int current = nextFirst + 1;
        while (current != nextLast) {
            System.out.print(items[current] + " ");
            current = current + 1;
            if (current == aSize) {
                current = 0;
            }
        }
    }

    public T removeFirst() {
        if (size == 0) { return null; }

        int current = currentFirst();

        T removed = items[current];
        items[current] = null;
        size = size - 1;

        if (nextFirst + 1 == aSize) { nextFirst = 0; }
        else { nextFirst += 1; }

        resize();
        return removed;
    }

    public T removeLast() {
        if (size == 0) { return null; }

        int current = currentLast();

        T removed = items[current];
        items[current] = null;
        size = size - 1;

        if (nextLast - 1 < 0) { nextLast = aSize - 1; }
        else { nextLast -= 1; }

        resize();
        return removed;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int pos = nextFirst + 1 + index;
        if (pos >= aSize) {
            pos = pos - aSize;
        }
        return items[pos];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof ArrayDeque) {
            if (((ArrayDeque<T>) o).size != this.size) {
                return false;
            }
            for (int i = 0; i < size; i +=1 ) {
                if (((ArrayDeque<T>) o).get(i) != this.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = nextFirst + 1;
            if (wizPos == aSize) {
                wizPos = 0;
            }
        }

        @Override
        public boolean hasNext() {
            return wizPos != nextLast;
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            if (wizPos == aSize) {
                wizPos = 0;
            }
            return returnItem;
        }
    }
}