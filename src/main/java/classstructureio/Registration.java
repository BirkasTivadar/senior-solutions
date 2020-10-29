package classstructureio;

import java.util.Scanner;

public class Registration {

    public static void main(String[] args) {

        System.out.println("Registration\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Az ön neve?");
        String name = scanner.nextLine();

        System.out.println("Az ön email címe?");
        String email = scanner.nextLine();

        System.out.println();

        System.out.println("Az ön neve: " + name);
        System.out.println("Az ön email címe: " + email);


    }
}
