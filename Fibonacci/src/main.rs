use std::fs;
use std::time::Instant;
use libc::{rusage, getrusage, RUSAGE_SELF};

fn fib(n: u64) -> u64 {
    if n == 1 || n == 2 {
        return n - 1;
    }
    fib(n - 1) + fib(n - 2)
}

/// Reads the peak Resident Set Size (VmHWM) in KB directly from the OS
fn get_peak_rss_kb() -> u64 {
    let status = fs::read_to_string("/proc/self/status").unwrap();
    for line in status.lines() {
        if line.starts_with("VmHWM:") {
            let parts: Vec<&str> = line.split_whitespace().collect();
            return parts[1].parse::<u64>().unwrap(); // already in KB
        }
    }
    0
}

/// Gets total CPU time in seconds (user + system)
fn get_cpu_time_seconds() -> f64 {
    unsafe {
        let mut usage: rusage = std::mem::zeroed();
        getrusage(RUSAGE_SELF, &mut usage);
        let user_sec = usage.ru_utime.tv_sec as f64 + usage.ru_utime.tv_usec as f64 / 1_000_000.0;
        let sys_sec = usage.ru_stime.tv_sec as f64 + usage.ru_stime.tv_usec as f64 / 1_000_000.0;
        user_sec + sys_sec
    }
}

fn main() {
    let start_wall = Instant::now();
    let start_cpu = get_cpu_time_seconds();

    let result = fib(40);

    let wall_elapsed = start_wall.elapsed();
    let end_cpu = get_cpu_time_seconds();
    let peak_mem = get_peak_rss_kb();

    println!("Language : Rust");
    println!("Fibonacci(40) = {}", result);
    println!("Execution time (wall): {:.6} seconds", wall_elapsed.as_secs_f64());
    println!("CPU time: {:.6} seconds", end_cpu - start_cpu);
    println!("Memory used: {} KB", peak_mem);
}
