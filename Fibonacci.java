import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Fibonacci {

    public static void main(String[] args) {
        int n = 40;

        // Get runtime object to measure memory usage
        Runtime runtime = Runtime.getRuntime();
        //runtime.gc();  // Run garbage collector before measurement

        // Get thread management for CPU time measurement
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        threadMXBean.setThreadContentionMonitoringEnabled(true);

        // Capture the initial CPU time (for the current thread)
        long startCpuTime = threadMXBean.getCurrentThreadCpuTime();

        // Capture memory and time measurements
        long startMemory = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        int val = fibonacci(n);

        // Capture time and memory after computation
        long endTime = System.nanoTime();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        // Capture the final CPU time
        long endCpuTime = threadMXBean.getCurrentThreadCpuTime();

        long memoryUsed = endMemory - startMemory;
        double timeMillis = (endTime - startTime) / 1_000_000.0;
        double cpuMillis = (endCpuTime - startCpuTime) / 1_000_000.0;

        // Output results
        System.out.println("Fibonacci(" + n + ") = " + val);
        System.out.printf("Execution Time (Wall Clock): %.3f ms\n", timeMillis);
        System.out.printf("CPU Time: %.3f ms\n", cpuMillis);
        System.out.printf("Memory Used: %.2f KB\n", memoryUsed / 1024.0);
        System.out.printf("Memory: %.2f KB\n", startMemory / 1024.0);
        System.out.printf("Memory: %.2f KB\n", endMemory / 1024.0);
    }

    public static int fibonacci(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
