import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Fibonacci {

    public static void main(String[] args) {
        int n = 40;

        // Get thread management for CPU time measurement
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        threadMXBean.setThreadContentionMonitoringEnabled(true);

        // Capture the initial CPU time (for the current thread)
        long startCpuTime = threadMXBean.getCurrentThreadCpuTime();

        // Capture time measurements
        long startTime = System.nanoTime();

        int val = fibonacci(n);

        // Capture time after computation
        long endTime = System.nanoTime();

        // Capture the final CPU time
        long endCpuTime = threadMXBean.getCurrentThreadCpuTime();

        double timeMillis = (endTime - startTime) / 1_000_000.0;
        double cpuMillis = (endCpuTime - startCpuTime) / 1_000_000.0;

        // Output results
        System.out.println("Fibonacci(" + n + ") = " + val);
        System.out.printf("Execution Time (Wall Clock): %.3f ms\n", timeMillis);
        System.out.printf("CPU Time: %.3f ms\n", cpuMillis);
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
