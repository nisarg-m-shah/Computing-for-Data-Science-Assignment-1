package main

import (
	"fmt"
	"runtime"
	"time"
)

// Recursive Fibonacci
func fibonacci(n int64) int64 {
	if n == 1 {
		return 0
	} else if n == 2 {
		return 1
	}
	return fibonacci(n-1) + fibonacci(n-2)
}

func main() {
	var n int64 = 40 // Adjust as needed

	// Record memory before
	var memBefore runtime.MemStats
	runtime.ReadMemStats(&memBefore)

	// Record time before
	start := time.Now()

	// Run Fibonacci
	result := fibonacci(n)

	// Record time after
	elapsed := time.Since(start)

	// Record memory after
	var memAfter runtime.MemStats
	runtime.ReadMemStats(&memAfter)

	// Calculate memory used
	memUsed := float64(memAfter.TotalAlloc-memBefore.TotalAlloc) / 1024 // in KB

	// Print results
	fmt.Printf("Fibonacci(%d) = %d\n", n, result)
	fmt.Printf("Execution Time (Wall Clock): %v\n", elapsed)
	fmt.Printf("Approx CPU Time (same as wall): %v\n", elapsed)
	fmt.Printf("Memory Used: %.2f KB\n", memUsed)
}
