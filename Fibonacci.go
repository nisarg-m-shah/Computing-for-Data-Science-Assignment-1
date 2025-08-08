package main

import (
	"fmt"
	"os"
	"runtime"
	"strconv"
	"strings"
	"time"
)

func fib(n int) int {
	if n == 1 || n == 2 {
		return n - 1
	}
	return fib(n-1) + fib(n-2)
}

func getCPUTimeSeconds() float64 {
	data, err := os.ReadFile("/proc/self/stat")
	if err != nil {
		return 0
	}
	fields := strings.Fields(string(data))
	// utime = field 14, stime = field 15 (0-based index 13 and 14)
	utimeTicks, _ := strconv.ParseFloat(fields[13], 64)
	stimeTicks, _ := strconv.ParseFloat(fields[14], 64)

	// Most Linux systems have 100 ticks/sec, but let's detect if possible
	ticksPerSec := float64(100) // adjust if needed for your OS

	return (utimeTicks + stimeTicks) / ticksPerSec
}

func main() {
	var mStart, mEnd runtime.MemStats
	runtime.ReadMemStats(&mStart)

	startWall := time.Now()
	startCPU := getCPUTimeSeconds()

	result := fib(40)

	endCPU := getCPUTimeSeconds()
	endWall := time.Since(startWall)

	runtime.ReadMemStats(&mEnd)
	usedMem := mEnd.Alloc - mStart.Alloc

	fmt.Printf("Fibonacci(40) = %d\n", result)
	fmt.Printf("Execution time: %.6f seconds\n", endWall.Seconds())
	fmt.Printf("CPU time: %.6f seconds\n", endCPU-startCPU)
	fmt.Printf("Memory used: %d KB\n", usedMem/1024)
}
