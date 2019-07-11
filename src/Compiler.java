import ast.program.Program;
import cg.CodeGenerator;

import java.io.FileReader;

public class Compiler {

    private static final String INPUT_FILE = "input.c";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new FileReader(INPUT_FILE));
        Parser parser = new Parser(scanner);
        CodeGenerator.initClass();
        parser.parse();
        Program.getInstance().compile();
        CodeGenerator.writeFinalClassCode();
    }

}
