package classstructureattributes;

import java.util.Scanner;

public class Song {
    String band;
    String title;
    int length;
}

class Music {

    public static void main(String[] args) {

        Song favoriteSong = new Song();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Kedvenc zeneszámának szerzője? ");
        favoriteSong.band = scanner.nextLine();
        System.out.println();

        System.out.println("Kedvenc zeneszámának címe? ");
        favoriteSong.title = scanner.nextLine();
        System.out.println();

        System.out.println("Kedvenc zeneszámának hossza? ");
        favoriteSong.length = scanner.nextInt();
        System.out.println();

        System.out.println("Az ön kedvenc zeneszámának adatai:");
        System.out.println();
        System.out.println(favoriteSong.band + " - " + favoriteSong.title + " (" + favoriteSong.length + " perc)" );





    }


}
