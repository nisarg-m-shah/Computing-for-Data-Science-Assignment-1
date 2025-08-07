import time
import tracemalloc
import os
import psutil

# Recursive Fibonacci
def fibonacci(n):
    if n == 1:
        return 0
    elif n == 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)

n = 40  # Set your desired Fibonacci number

# Track CPU process info
process = psutil.Process(os.getpid())

# Start time and memory tracking
start_wall = time.time()                    # Execution (wall) time
start_cpu = time.process_time()            # CPU time
tracemalloc.start()                        # Start memory tracking

# Run the algorithm
result = fibonacci(n)
print(f"Fibonacci({n}) = {result}")

# End time and memory
end_wall = time.time()
end_cpu = time.process_time()
current, peak = tracemalloc.get_traced_memory()
tracemalloc.stop()

# Get memory used by process (in KB)
memory_info = process.memory_info()
rss_memory_kb = memory_info.rss / 1024  # Resident Set Size

# Round and display results
print("Execution (Wall) Time:", round(end_wall - start_wall, 3), "seconds")
print("CPU Time:", round(end_cpu - start_cpu, 3), "seconds")
print("Current Memory Traced:", round(current / 1024, 3), "KB")
print("Peak Memory Traced:", round(peak / 1024, 3), "KB")
print("Total RSS Memory Used by Process:", round(rss_memory_kb, 3), "KB")
