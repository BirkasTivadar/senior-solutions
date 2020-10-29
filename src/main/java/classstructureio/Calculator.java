package classstructureio;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        System.out.println("Calculator\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Kérem az első számot: ");
        int number1 = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Kérem a második számot: ");
        int number2 = scanner.nextInt();

        int osszeg = number1 + number2;

        System.out.println();
        System.out.println("A " + number1 + " és a " + number2 + " összege: " + osszeg);
    }
}
