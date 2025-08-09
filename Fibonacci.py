import time
import resource

def fib(n):
    if n == 1 or n == 2:
        return n - 1
    return fib(n-1) + fib(n-2)

# Record start
start_wall = time.perf_counter()
start_cpu = time.process_time()

result = fib(40)

# Record end
end_cpu = time.process_time()
end_wall = time.perf_counter()

# Memory usage in kilobytes
usage = resource.getrusage(resource.RUSAGE_SELF).ru_maxrss

print(f"Language: Python")
print(f"Fibonacci(40) = {result}")
print(f"Execution time: {end_wall - start_wall:.6f} seconds")
print(f"CPU time: {end_cpu - start_cpu:.6f} seconds")
print(f"Memory used: {usage} KB")
