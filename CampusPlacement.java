import java.util.Arrays;
import java.util.Scanner;

public class CampusPlacement {

    // Nested Candidate Class
    static class Candidate {
        int candidateId;
        String name;
        int aptitude;
        int technical;
        int communication;

        public Candidate(int candidateId, String name, int aptitude, int technical, int communication) {
            this.candidateId = candidateId;
            this.name = name;
            this.aptitude = aptitude;
            this.technical = technical;
            this.communication = communication;
        }

        public int getTotalScore() {
            return this.aptitude + this.technical + this.communication;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (!scanner.hasNextInt()) return;
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            Candidate[] candidates = new Candidate[n];

            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                String name = scanner.next();
                int apt = scanner.nextInt();
                int tech = scanner.nextInt();
                int comm = scanner.nextInt();
                candidates[i] = new Candidate(id, name, apt, tech, comm);
            }

            Arrays.sort(candidates, (c1, c2) -> {
                int score1 = c1.getTotalScore();
                int score2 = c2.getTotalScore();
                if (score1 != score2) {
                    return Integer.compare(score2, score1);
                }
                return Integer.compare(c1.candidateId, c2.candidateId);
            });

            for (int i = 0; i < k; i++) {
                System.out.println(candidates[i].candidateId + " " + candidates[i].name + " " + candidates[i].getTotalScore());
            }
        }
    }
}


