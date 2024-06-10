import java.io.BufferedReader;                          // Reads text from an input stream
import java.io.InputStreamReader;
import java.io.IOException;                             // General class of I/O exceptions
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;                                  // Ordered collection


public class Lox {
    public static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);                            // EX_USAGE (from UNIX <sysexits.h>)
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {                                        // Run without arguments
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) {
            System.exit(65);                            // EX_DATAERR
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.println("> ");
            String line = reader.readLine();
            if (line == null) break;                    // Check for Ctrl-d (end-of-file)
            run(line);
            hadError = false;                           // Reset hadError
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now just print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println(
                "[line" + line + "] Error" + where + ": " + message
        );
        hadError = true;
    }
}