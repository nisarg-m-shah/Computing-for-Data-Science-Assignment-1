import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Fibonacci {
    public static long fib(long n) {
        if (n == 1 || n == 2) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        long startWall = System.nanoTime();
        long startCpu = bean.getCurrentThreadCpuTime();

        long result = fib(40);

        long endCpu = bean.getCurrentThreadCpuTime();
        long endWall = System.nanoTime();

        // Memory usage in bytes
        long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("Fibonacci(40) = " + result);
        System.out.printf("Execution time: %.6f seconds%n", (endWall - startWall) / 1e9);
        System.out.printf("CPU time: %.6f seconds%n", (endCpu - startCpu) / 1e9);
        System.out.println("Memory used: " + usedMem / 1024 + " KB");
    }
}
