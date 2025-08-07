fn fibonacci(n: u128) -> u128 {
    if n == 1 {
        0
    } else if n == 2 {
        1
    } else {
        fibonacci(n - 1) + fibonacci(n - 2)
    }
}

fn main() {
    let n = 40;
    println!("Fibonacci({}) = {}", n, fibonacci(n));
}
