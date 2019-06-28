import symbol.Symbol;

import java.io.*;

public class Main {

    private static final String HEAD_OF_HTML = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n<title>code.out</title>\n</head>\n<body>\n";
    private static final String HTML_END = "</body>\n</html>";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("code.c"));
        StringBuilder html = new StringBuilder(HEAD_OF_HTML);
        for (Symbol symbol = scanner.scan(); symbol != null; symbol = scanner.scan())
            html.append(scanner.string);
        html.append(HTML_END);

        FileWriter fileWriter = new FileWriter("scanned.html");
        fileWriter.write(html.toString());
        fileWriter.flush();
        fileWriter.close();

        System.out.println("Finished scanning");
    }

}
