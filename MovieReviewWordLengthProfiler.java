import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter review:");
        String review = sc.nextLine();

        String[] words = review.split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            int len = word.length();

            if (len <= 4)
                shortCount++;
            else if (len <= 8)
                mediumCount++;
            else
                longCount++;
        }

        System.out.println("Short: " + shortCount +
                           " | Medium: " + mediumCount +
                           " | Long: " + longCount);

        sc.close();
    }
}