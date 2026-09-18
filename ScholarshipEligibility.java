import java.util.Scanner;
public class ScholarshipEligibility {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double cgpa;
        double attendance;
        double familyIncome;
        System.out.print("Enter CGPA: ");
        cgpa = sc.nextDouble();
        System.out.print("Enter Attendance Percentage: ");
        attendance = sc.nextDouble();
        System.out.print("Enter Family Income: ");
        familyIncome = sc.nextDouble();
        if (cgpa >= 8.5 && attendance >= 85 && familyIncome <= 500000) {
            System.out.println("Eligible");
            if (cgpa >= 9.5) {
                System.out.println("Scholarship: 50000");
            }
            else if (cgpa >= 9.0) {
                System.out.println("Scholarship: 35000");
            }
            else {
                System.out.println("Scholarship: 20000");
            }
        } else {
            System.out.println("Not Eligible");
        }
        sc.close();
    }
}
