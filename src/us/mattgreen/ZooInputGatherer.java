package us.mattgreen;

import java.util.ArrayList;
import java.util.Scanner;

public class ZooInputGatherer {
    private final Scanner keyboard = new Scanner(System.in);

    public void addNewAnimal(ArrayList<Talkable> zoo) {
        zoo.add(AskAnimalType());
    }

    private Talkable AskAnimalType() {
        System.out.println("Which type of animal do you want to create?");
        while (true) {
            String userInput = keyboard.nextLine();
            switch (userInput.toLowerCase()) {
                case "cat" -> {
                    return CreateCat();
                }
                case "dog" -> {
                    return CreateDog();
                }
                case "teacher" -> {
                    return CreateTeacher();
                }
                default -> System.out.println("Not a valid animal type!");
            }
        }
    }

    private Cat CreateCat() {
        System.out.println("What is your cat's name?");
        String name = validateName();
        System.out.printf("How many mice has %s killed?\n", name);
        int mousesKilled = validatePositiveInt();
        return new Cat(mousesKilled, name);
    }

    private Dog CreateDog() {
        final char TRUE_VALUE = 'y';
        final char FALSE_VALUE = 'n';
        final String ERR_MESSAGE = "Not a valid option!";

        System.out.println("What is your dog's name?");
        String name = validateName();
        System.out.printf("Is %s a friendly dog? (%s/%s)\n", name, TRUE_VALUE, FALSE_VALUE);
        while (true) {
            char userInput = keyboard.nextLine().toLowerCase().charAt(0);
            if (userInput == TRUE_VALUE) {
                return new Dog(true, name);
            }
            if (userInput == FALSE_VALUE) {
                return new Dog(false, name);
            }
            System.out.println(ERR_MESSAGE);
        }
    }

    private Teacher CreateTeacher() {
        System.out.println("What is your teacher's name?");
        String name = validateName();
        System.out.printf("How old is %s?\n", name);
        int age = validatePositiveInt();
        return new Teacher(age, name);
    }

    private String validateName() {
        final String ERR_MESSAGE = "Not a valid name!";

        while (true) {
            String userInput = keyboard.nextLine();
            if (userInput != null && !userInput.isEmpty()) {
                return userInput;
            }
            System.out.println(ERR_MESSAGE);
        }
    }

    private int validatePositiveInt() {
        final String ERR_MESSAGE = "Not a valid number!";

        do {
            try {
                int userInt = Integer.parseInt(keyboard.nextLine());
                if (userInt >= 0) {
                    return userInt;
                }
                System.out.println(ERR_MESSAGE);
            } catch (Exception e) {
                System.out.println(ERR_MESSAGE);
            }
        } while (true);
    }
}
