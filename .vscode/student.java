public class student {
    String name;
    int roll;

    student(String n, int r) {
        name = n;
        roll = r;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + roll);
    }

    public static void main(String[] args) {
        student s1 = new student("Rahul", 205);
        s1.display();
    }
}