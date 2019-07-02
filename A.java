public class A {

    public static void main(String[] args) {
        boolean b;
        int a = 5;
        long b = 10;
        System.out.println(a ^ b);
        do {
            a += 3;
            b = a < 10;
            boolean c = a >= 20.5;
            boolean d = c && b;
        } while (b);
    }

}