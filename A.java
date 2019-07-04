public class A {

    public static void main(String[] args) {
        int a = 2;
        switch (a) {
            case 1:
                a = 3;
                break;
            case 3:
                a = 4;
                break;
            case 2:
                a += 3;
                break;
            case 10:
                break;
        }
    }

    static void test() {
        int a = 3;
    }

}