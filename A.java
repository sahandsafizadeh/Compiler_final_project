public class A {

    public static void main(String[] args) {
        fib(10);
    }

    private static long fib(int n) {
        return n < 2 ? n : fib(n - 1) + fib(n - 2);
    }

}
