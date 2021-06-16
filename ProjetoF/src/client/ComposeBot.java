package client;
import java.util.Scanner;

public class ComposeBot {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String strategy = sc.nextLine();
        long seed = sc.nextLong();

        ICompose composer = null;
        if (strategy.equals("A")) composer = new ComposeA();
        if (strategy.equals("B")) composer = new ComposeB();

        String lick = composer.compose(seed);

        //Print scale, chords, etc
        System.out.println(composer.scale().name());
        System.out.println(composer.scale().arrayOfNotesToString());
        System.out.println(composer.scale().progressionToString());
        System.out.println(lick);
    }
}
