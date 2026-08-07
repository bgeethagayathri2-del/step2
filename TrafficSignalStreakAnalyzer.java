public class TrafficSignalStreakAnalyzer {

    public static void findLongestStreak(String signalLog) {

        if (signalLog == null || signalLog.length() == 0) {
            System.out.println("Signal log is empty.");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int longestLength = 1;

        char currentColor = signalLog.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) == currentColor) {
                currentLength++;
            } else {
                if (currentLength > longestLength) {
                    longestLength = currentLength;
                    longestColor = currentColor;
                }

                currentColor = signalLog.charAt(i);
                currentLength = 1;
            }
        }

        // Check the last streak
        if (currentLength > longestLength) {
            longestLength = currentLength;
            longestColor = currentColor;
        }

        System.out.println("Longest Streak: '" + longestColor +
                "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {

        // Sample Test Case 1
        String signal1 = "RRGGGYRR";
        findLongestStreak(signal1);

        // Sample Test Case 2
        String signal2 = "RRRRYYGG";
        findLongestStreak(signal2);
    }
}