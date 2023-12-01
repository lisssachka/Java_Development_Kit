package geekbrains;
/**
 * Класс, реализующий интерфейс Runnable.
 */
public class Philosopher implements Runnable {
    private int eating = 3;        // Количество приемов пищи.
    private final Fork leftFork;   // Левая вилка.
    private final Fork rightFork;  // Правая вилка.
    private final String name;     // Имя философа.
    private final long timeout;    // Время выполнения действия.

    /**
     * Конструктор класса:
     * @param leftFork  - левая вилка.
     * @param rightFork - правая вилка.
     * @param name      - имя философа.
     */
    public Philosopher(Fork leftFork, Fork rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
        timeout = System.nanoTime();
    }

    /**
     * Класс run реализует алгоритм, в соответствии с которым философы думают,
     * затем первый из них берет две вилки и начинает прием пищи. Один из последующих философов,
     * который имеет возможность взять две вилки, также берет их и приступает к приему пищи. Остальные - думают.
     * Завершив трапезу, философы кладут вилки и начинают думать. Их соседи, получившие возможность взять по две вилки,
     * берут их и начинают кушать.
     * После трех приемов пищи у каждого участника наступает чувство сытости.
     * Когда наелись все, программа прекращает работу.
     */
    @Override
    public void run() {
        int count = 0;
        while (eating > 0) {
            try {
                activity("думает", 3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (rightFork) {
                synchronized (leftFork) {
                    try {
                        activity("взял вилки " + leftFork.number + " и " + rightFork.number, 500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        activity("кушает ", 2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    eating--;
                    count++;
                    try {
                        activity("положил вилки " + leftFork.number + " и " + rightFork.number, 500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        activity("покушал " + count + " раза и наелся.\n" );
    }


    private void activity(String act, long timeout) throws InterruptedException {
        activity(act);
        Thread.sleep((int) (Math.random() * timeout));

    }

    private void activity(String act) {
        long time = (System.nanoTime() - timeout) / 10_000_000;
        System.out.printf("%s философ %s - %s\n", time, name, act);

    }
}
