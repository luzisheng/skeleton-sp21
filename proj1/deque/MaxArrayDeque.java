package deque;

import java.util.Comparator;
import java.util.Arrays;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T currentMax = get(0);
        int maxSize = size();
        for (int i = 0; i < maxSize; i += 1) {
            if(comparator.compare(get(i), currentMax) > 0) {
                currentMax = get(i);
            }
        }
        return currentMax;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T currentMax = get(0);
        int maxSize = size();
        for (int i = 0; i < maxSize; i += 1) {
            if(c.compare(get(i), currentMax) > 0) {
                currentMax = get(i);
            }
        }
        return currentMax;
    }
}
