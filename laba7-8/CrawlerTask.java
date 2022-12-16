import java.io.*;
import java.net.*;
import java.util.regex.*;

/**
 Этот класс сканирует веб-сайты
 и добавляет новые ссылки в пул для обработки другими потоками
 */

public class CrawlerTask implements Runnable {
    //Регулярное выражение для всего href HTML tag
    public static final String LINK_REGEX = "href\\s*=\\s*\"([^$^\"]*)\"";
    public static final Pattern LINK_PATTERN = Pattern.compile(LINK_REGEX, Pattern.CASE_INSENSITIVE);
    public static int maxRunTime = 5; //Продолжительность в секундах, в течение которой сокет будет ожидать сервер
    private URLPool pool;

    public CrawlerTask(URLPool p) {
        pool = p;
    }

    /**
     * Создает сокет для отправки HTTP-запроса на веб-страницу URLDepthPair
     */
    public Socket sendRequest(URLDepthPair nextPair)
            throws IOException {
        //Создание нового HTTP сокета
        Socket socket = new Socket(nextPair.getHost(), 80);
        socket.setSoTimeout(maxRunTime * 1000);

        OutputStream os = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(os, true);

        //Запрос ресурсов у хоста страницы
        writer.println("GET " + nextPair.getDocPath() + " HTTP/1.1");
        writer.println("Host: " + nextPair.getHost());
        writer.println("Connection: close");
        writer.println();

        return socket;
    }

    /**
     * Обработка URL-адреса, с помощью нахождения всех ссылок и их
     * добавления в общий пул URL-адресов
     */
    public void processURL(URLDepthPair url) throws IOException {
        Socket socket;
        try {
            socket = sendRequest(url);
        }
        catch (UnknownHostException e) {
            System.err.println("Host "+ url.getHost() + " couldn't be identified");
            return;
        }
        catch (SocketException e) {
            System.err.println("Error with socket connection: " + url.getURL() + " - " + e.getMessage());
            return;
        }
        catch (IOException e) {
            System.err.println("Couldn't retrieve page at " + url.getURL() + " - " + e.getMessage());
            return;
        }

        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	

        String line;
        //цикл идет то момента, когда в пуле не останется пар URL-Depth
        while ((line = reader.readLine()) != null) { //получение пары из пула
            Matcher LinkFinder = LINK_PATTERN.matcher(line); //получение веб-страницы по адресу
            while (LinkFinder.find()) { //поиск других адресов
                String newURL = LinkFinder.group(1);
                URL newSite;
                try {
                    if (URLDepthPair.isAbsolute(newURL)) {
                        newSite = new URL(newURL);
                    }
                    else {
                        newSite = new URL(url.getURL(), newURL);
                    }
                    pool.add(new URLDepthPair(newSite, url.getDepth() + 1)); //добаление к пулу новой пары (+1 к прошлой глубине)
                }
                catch (MalformedURLException e) {
                    System.err.println("Error in URL - " + e.getMessage());
                }
            }
        }
        reader.close();

        //Закрытие сокета
        try {
            socket.close();
        }
        catch (IOException e) {
            System.err.println("Couldn't stop connection to " + url.getHost() + " - " + e.getMessage());
        }
    }

    /**
     * Обработка первого URL-адреса в пуле
     */
    public void run() {
        URLDepthPair nextPair;
        while (true) {
            nextPair = pool.get();
            try {
                processURL(nextPair);
            }
            catch (IOException e) {
                System.err.println("Error in reading the page at " + nextPair.getURL() + " - " + e.getMessage());
            }
        }
    }
}