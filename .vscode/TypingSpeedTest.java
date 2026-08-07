public class TypingSpeedTest {

    public static void checkTypingAccuracy(String original, String typed) {

        if (original.length() != typed.length()) {
            System.out.println("Error: Both strings must have equal length.");
            return;
        }

        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i + 1; // Position starts from 1
            }
        }

        double accuracy = (matched * 100.0) / original.length();

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%",
                matched, original.length(), accuracy);

        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            System.out.println(" | First Mismatch at position " + firstMismatch +
                    " ('" + original.charAt(firstMismatch - 1) +
                    "' vs '" + typed.charAt(firstMismatch - 1) + "')");
        }
    }

    public static void main(String[] args) {

        // Sample Test Case 1
        String original1 = "hello world";
        String typed1 = "hello worlt";
        checkTypingAccuracy(original1, typed1);

        // Sample Test Case 2
        String original2 = "coding";
        String typed2 = "coding";
        checkTypingAccuracy(original2, typed2);
    }
}