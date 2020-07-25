import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Chef chef = new Chef();
        System.out.printf("The form for %s is completed. " +
                "We will contact you if we need a chef that cooks %s dishes.",
                chef.firstName, chef.cuisinePref);
    }

    static class Chef {
        String firstName;
        int age;
        String stageOfEducation;
        int yearsOfExperience;
        String cuisinePref;

        public Chef() {
            Scanner input = new Scanner(System.in);
            firstName = input.next();
            age = input.nextInt();
            stageOfEducation = input.next();
            yearsOfExperience = input.nextInt();
            cuisinePref = input.next();
        }
    }
}