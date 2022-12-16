import java.util.*;

/**
 * Отслеживает URL-адреса, которые необходимо обработать,
 * а также URL-адреса, которые уже были просмотрены
 */
public class URLPool {
    private int maxDepth;
    //Текущее количество потоков при вызове wait()
    private int waitCounter = 0;

    private LinkedList<URLDepthPair> unprocessedURLs; //необработанные URL-адреса
    private LinkedList<URLDepthPair> processedURLs; //обработанные URL-адреса
    private HashSet<String> seenURLs; //только новые, уникальные URL-адреса

    /**
     * Создает URLPool с заданной максимальной глубиной
     */
    public URLPool(int max) {
        unprocessedURLs = new LinkedList<>();
        processedURLs = new LinkedList<>();
        seenURLs = new HashSet<>();
        maxDepth = max;
    }

    public synchronized int getWaitCounter() { //возвращает количество "ожидающих" потоков
        return waitCounter;
    }

    public synchronized void add(URLDepthPair nextPair) {
        String newURL = nextPair.getURL().toString();

        //Удаляет любой "висячий" "/" из URL-адреса
        String trimURL = (newURL.endsWith("/")) ? newURL.substring(0, newURL.length() -1) : newURL;
        if (seenURLs.contains(trimURL)){
            return;
        }
        seenURLs.add(trimURL);

        if (nextPair.getDepth() < maxDepth) {
            unprocessedURLs.add(nextPair);
            notify(); //в случае появления и добавления нового URL-адреса к пулу
        }
        processedURLs.add(nextPair);
    }

    public synchronized URLDepthPair get() {
        //Приостановить поток до тех пор, пока не будет добавлен новый URL
        while (unprocessedURLs.size() == 0) {
            waitCounter++; //увеличение перед вызовом wait()
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println("Ignore unexpected InterruptedException - " + e.getMessage());
            }
            waitCounter--; //уменьшение после выхода из режима "ожидания"
        }
        return unprocessedURLs.removeFirst();
    }

    /**
     * Выводит все обработанные URL-адреса
     */
    public synchronized void printURLs() {
        System.out.println("\nUnique URLs Were Found: " + processedURLs.size());
        while (!processedURLs.isEmpty()) {
            System.out.println(processedURLs.removeFirst());
        }
    }
}