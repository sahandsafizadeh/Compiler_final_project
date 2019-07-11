public class A {

    int a;

    public static void main(String[] args) {
        A a = new A();
        a.a = 2;
        int b = a.a;
        args = new String[10];
        args[0] = "ali";
        System.out.println(args[0]);
    }

}
