package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.K;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        SLList<Integer> speedTest;
        int ops;
        Stopwatch sw;
        for (int i = 1000; i <= 64000; i *= 2) {
            Ns.addLast(i);
            ops = 0;
            speedTest = new SLList<>();
            for (int k = 0; k < i; k += 1) {
                speedTest.addLast(1);
            }
            sw = new Stopwatch();
            for(int j = 0; j < 10000; j += 1) {
                speedTest.getLast();
                ops += 1;
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(ops);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
