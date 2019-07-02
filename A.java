public class A {

    public static void main(String[] args) {
        boolean b;
        int a = 5;
        do {
            a += 3;
            b = a < 10;
        } while (b);
    }

}