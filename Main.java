import java.util.Scanner;

public class Main {

    static String normalizeCode(String raw) {

        raw = raw.trim();

        String publisher = raw.substring(0, 3).toUpperCase();

        String rest = raw.substring(3);

        return publisher + rest;
    }

    static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: remaining 10 characters must be digits";
            }
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        return "[" + publisher + "] YEAR: " + year
                + " | CATALOG: " + catalog;
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter ISBN code: ");

            String input = sc.nextLine();

            String code = normalizeCode(input);

            String result = validateAndFormat(code);

            System.out.println(result);
        }
    }
}