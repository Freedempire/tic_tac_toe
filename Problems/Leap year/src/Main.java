import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner input = new Scanner(System.in);
        int year = input.nextInt();

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            System.out.println("Leap");
        else
            System.out.println("Regular");
    }
}