import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String fileName = "src/lab2test.txt";
            //initialize automata
            NFA nfa = new NFA(getScanner(fileName));


            System.out.format("Enter w1: ");
            String w1 = scanner.next();
            Set<Integer> afterW1 = nfa.processWord(w1);
            Set<Integer> reachableFromAfterW1 = nfa.getReachableFromStates(afterW1);

            System.out.format("Enter w2: ");
            String w2 = scanner.next();
            w2 = new StringBuilder(w2).reverse().toString();
            NFA infa = nfa.inverse();
            Set<Integer> beforeW2 = infa.processWordFromStates(w2, infa.finalStates);

            reachableFromAfterW1.retainAll(beforeW2);
            if (!reachableFromAfterW1.isEmpty()) {
                System.out.println("There DOES exist w1, w2 such that w1w0w2 is acceptable.");
            } else {
                System.out.println("There does NOT exist w1, w2 such that w1w0w2 is acceptable.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid file pathname");
        }

    }
    static Scanner getScanner(String pathname) throws FileNotFoundException {
        File file = new File(pathname);

        if (!file.exists()) {
            System.out.format("File '%s' does not exist.%n", pathname);
        }

        if (!file.canRead()) {
            System.out.format("Cannot read file '%s'.%n", pathname);
        }

        return new Scanner(file);
    }
}
