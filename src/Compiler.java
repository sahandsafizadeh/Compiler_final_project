import java.io.FileReader;

public class Compiler {

    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(new Scanner(new FileReader("inputFiles/expr.c")));
        parser.parse();

        int a = 3;
        int b = 5;
        int c = a + b;
    }

}
