package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeTest {

    @Test
    public void testThreeAddThreeRemove() {
        LinkedListDeque<Integer> correct = new LinkedListDeque<>();
        ArrayDeque<Integer> broken = new ArrayDeque<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> correct = new LinkedListDeque<>();
        ArrayDeque<Integer> broken = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            // addFirst addLast isEmpty size removeFirst removeLast get
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                correct.addFirst(randVal);
                broken.addFirst(randVal);
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 2) {
                // isEmpty
                assertEquals(correct.isEmpty(), broken.isEmpty());
            } else if (operationNumber == 3 && broken.size() != 0) {
                // size
                assertEquals(correct.size(), broken.size());
            } else if (operationNumber == 4 && broken.size() != 0) {
                // removeFirst
                int firstCorrect = correct.removeFirst();
                int firstBroken = broken.removeFirst();
                assertEquals(firstCorrect, firstBroken);
            } else if (operationNumber == 5 && broken.size() != 0) {
                // removeLast
                int lastCorrect = correct.removeLast();
                int lastBroken = broken.removeLast();
                assertEquals(lastCorrect, lastBroken);
            } else if (operationNumber == 6 && broken.size() != 0) {
                // get
                int randVal = StdRandom.uniform(0, broken.size());
                assertEquals(correct.get(randVal), broken.get(randVal));
            }
        }
    }

    @Test
    public void iterableTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        for (int i = 0; i < 10; i += 1) {
            int added = StdRandom.uniform(0, 20);
            lld1.addFirst(added);
        }

        for (int x : lld1) {
            System.out.print(x + " ");
        }
    }
}
