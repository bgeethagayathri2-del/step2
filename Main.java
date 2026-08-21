import java.util.Scanner;

public class Main {

    static void parseInventoryRecord(String csvLine) {

        // Remove quotes if present
        csvLine = csvLine.replace("\"", "");

        // Split using comma
        String[] parts = csvLine.split(",");

        // Check if exactly 3 fields are present
        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = parts[0];
        String sku = parts[1];
        String quantity = parts[2];

        System.out.println("Product: " + productName +
                           " | SKU: " + sku +
                           " | Qty: " + quantity);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter inventory record: ");
        String input = sc.nextLine();

        parseInventoryRecord(input);

        sc.close();
    }
}