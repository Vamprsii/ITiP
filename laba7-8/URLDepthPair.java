import java.net.*;
import java.util.regex.*;

/**
 Хранит URL-адреса и глубину
 */
public class URLDepthPair {
    // Регулярное выражения для URL
    public static final String URL_REGEX = "(https?://)((\\w+\\.)+\\.(\\w)+[~:\\S/]*)"; //строковая константа
    public static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX,  Pattern.CASE_INSENSITIVE);
    private URL URL;
    private int depth;

    public URLDepthPair(URL url, int d) throws MalformedURLException {
        //Предполагается, что входной URL-адрес является абсолютным URL-адресом
        URL = new URL(url.toString());
        depth = d;
    }

    @Override
    public String toString() { // метод упрощает вывод результатов веб-сканирования
        return "URL: " + URL.toString() + ", Depth: " + depth;
    }

    /**
     Возвращает URL пары
     */
    public URL getURL() {
        return URL;
    }

    /**
     Возвращает глубину поиска по URL-адресу
     */
    public int getDepth() {
        return depth;
    }

    /**
     Возвращает хост сервера, указанный в URL-адресе
     */
    public String getHost() {
        return URL.getHost();
    }

    /**
     * Возвращает путь указанного URL-адреса
     */
    public String getDocPath() {
        return URL.getPath();
    }

    /**
     Проверяет, является ли URL-адрес абсолютным
     */
    public static boolean isAbsolute(String url) {
        Matcher URLChecker = URL_PATTERN.matcher(url);
        return URLChecker.find();
    }
}