import java.net.*;

/**
 * Cоздает несколько потоков для обработки URL-адресов,
 * имеющих корни в указанном URL-адресе
 */

public class Crawler {
    private URLPool pool;

    public int threads = 4; // Количество потоков

    /**
     * Корневой URL-адрес должен включать протокол
     * (упрощает проверку посещенных URL-адресов)
     */
    public Crawler(String root, int max) throws MalformedURLException {
        pool = new URLPool(max);
        URL rootURL = new URL(root);
        pool.add(new URLDepthPair(rootURL, 0));
    }

    /**
     * Создание потоков CrawlerTask для обработки URL-адресов
     */
    public void crawl() {
        for (int i = 0; i < threads; i++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            Thread thread = new Thread(crawler);
            thread.start();
        }
        //Если все URL-адреса находятся в режиме "ожидания", то crawl() выполняется
        while (pool.getWaitCounter() != threads) { //завершаем при равенстве общего кол-ва потоков и потоков, возвращаемых соответствующим методом
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("Ignore unexpected InterruptedException - " +
                        e.getMessage());
            }
        }
        pool.printURLs();
    }

    /**
     * Создает сканер, который просматривает ссылки, начиная с корневого URL
     */
    public static void main(String[] args) {
        //оповещение о возникновении синтаксической ошибки
        if (args.length < 2 || args.length > 5) {
            System.err.println("Usage: java Crawler <URL> <depth> " +
                    "<patience> -t <threads>");
            System.exit(1);
        }

        //Вызов метода crawl() и создание экземпляра Crawler
        try {
            Crawler crawler = new Crawler(args[0], Integer.parseInt(args[1]));
            switch (args.length) {
                case 3 -> CrawlerTask.maxRunTime = Integer.parseInt(args[2]);
                case 4 -> crawler.threads = Integer.parseInt(args[3]);
                case 5 -> {
                    CrawlerTask.maxRunTime = Integer.parseInt(args[2]);
                    crawler.threads = Integer.parseInt(args[4]);
                }
            }
            crawler.crawl();
        }
        catch (MalformedURLException e) {
            System.err.println("Error: The URL " + args[0] + " is incorrect");
            System.exit(1);
        }
        System.exit(0);
    }
}