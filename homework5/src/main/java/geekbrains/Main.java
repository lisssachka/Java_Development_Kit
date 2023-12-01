package geekbrains;

import java.util.HashMap;
import java.util.Map;

/**
 * Main - точка входа в программу.
 */
public class Main {
    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Fork[] forks = new Fork[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i + 1);
        }
        for (int i = 0; i < forks.length; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % 5];
            if (i == 0) {
                philosophers[i] = new Philosopher(rightFork, leftFork, philName.get(i));
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork, philName.get(i));
            }
            Thread thread = new Thread(philosophers[i]);
            thread.start();
        }
    }

    /**
     * Коллекция философов - всего пять человек.
     */

    private static final Map<Integer, String> philName = new HashMap<Integer, String>() {{
        put(0, "Валуев");
        put(1, "Хабиб");
        put(2, "Али");
        put(3, "Емельяненко");
        put(4, "Цзю");
    }};
}