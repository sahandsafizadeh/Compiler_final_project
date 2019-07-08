import java.io.FileReader;

public class Compiler {

    public static void main(String[] args) throws Exception {
        Parser parser = new Parser(new Scanner(new FileReader("inputFiles/expr.c")));
        parser.parse();

//        Program.getInstance().compile();
    }

}
