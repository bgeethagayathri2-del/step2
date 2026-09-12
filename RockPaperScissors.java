import java.util.*;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};

        int playerWins = 0;
        int computerWins = 0;
        int draws = 0;

        // ✅ Try-with-resources ensures Scanner closes automatically
        try (Scanner sc = new Scanner(System.in)) {

            for (int round = 1; round <= 5; round++) {

                System.out.print("Enter Rock, Paper or Scissors: ");
                String playerMove = sc.next();

                String computerMove = moves[random.nextInt(3)];
                String result = playRound(playerMove, computerMove);

                System.out.println("Round " + round);
                System.out.println("Player: " + playerMove);
                System.out.println("Computer: " + computerMove);
                System.out.println("Result: " + result);

                // ✅ Rule switch (modern syntax)
                switch (result) {
                    case "Player Wins" -> playerWins++;
                    case "Computer Wins" -> computerWins++;
                    case "Draw" -> draws++;
                }
            }
        }

        // ✅ Flexible win percentage calculation
        int totalRounds = playerWins + computerWins + draws;
        double winPercentage = (playerWins / (double) totalRounds) * 100;

        System.out.println("\nFinal Summary");
        System.out.println("Wins: " + playerWins);
        System.out.println("Losses: " + computerWins);
        System.out.println("Draws: " + draws);
        System.out.println("Win %: " + winPercentage);
    }
}
