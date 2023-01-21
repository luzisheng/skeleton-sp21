package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> broken = new StudentArrayDeque<>();
        String log = "";

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            // addFirst addLast isEmpty size removeFirst removeLast get
            int operationNumber = StdRandom.uniform(0, 7);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                log = log + "addFirst(" + randVal + ")\n";
                correct.addFirst(randVal);
                broken.addFirst(randVal);
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                log = log + "addLast(" + randVal + ")\n";
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 2) {
                // isEmpty
                log = log + "isEmpty()\n";
                assertEquals(log, correct.isEmpty(), broken.isEmpty());
            } else if (operationNumber == 3) {
                // size
                log = log + "size()\n";
                assertEquals(log, correct.size(), broken.size());
            } else if (operationNumber == 4 && correct.size() != 0) {
                // removeFirst
                Integer firstCorrect = correct.removeFirst();
                Integer firstBroken = broken.removeFirst();
                log = log + "removeFirst()\n";
                assertEquals(log, firstCorrect, firstBroken);
            } else if (operationNumber == 5 && correct.size() != 0) {
                // removeLast
                log = log + "removeLast()\n";
                Integer lastCorrect = correct.removeLast();
                Integer lastBroken = broken.removeLast();
                assertEquals(log, lastCorrect, lastBroken);
            } else if (operationNumber == 6 && correct.size() != 0) {
                // get
                int randVal = StdRandom.uniform(0, broken.size());
                log = log + "get(" + randVal + ")\n";
                assertEquals(log, correct.get(randVal), broken.get(randVal));
            }
        }
    }
}
