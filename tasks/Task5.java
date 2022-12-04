import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class Task5 {

    public static void main(String[] args) {
        System.out.println("---1 task---");
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));
        System.out.println("---2 task---");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("---3 task---");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("---4 task---");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println("---5 task---");
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})));
        System.out.println("---6 task---");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("---7 task---");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println("---8 task---");
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println("---9 task---");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println("---10 task---");
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21));
    }


    //1 таск - код строки, где первый элемент - символьный код, остальные эелементы - различия между символами
    public static int[] encrypt(String s) {
        char lastAscii = 0;
        int[] ascii = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            ascii[i] = chars[i] - lastAscii;
            lastAscii = chars[i];
        }
        return ascii;
    }

    //1.2 таск - декодированное сообщение (код - массив)
    public static String decrypt(int[] s) {
        char lastAscii = 0;
        StringBuilder symbols = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            symbols.append((char) (lastAscii + s[i]));
            lastAscii = symbols.charAt(i);
        }
        return symbols.toString();
    }

    //2 таск - функция, принимающая имя шахматной фигуры, ее положение и целевую позицию
    // true, если фигура может двигаться к цели, и false, если она не может этого сделать
    public static boolean canMove(String figure, String x, String x1) {
        if (figure.equals("Pawn")) {
            if (x.charAt(0) == x1.charAt(0)) {
                if (x.charAt(1) == '2' && x1.charAt(1) == '4') {
                    return true;
                }
                if (x.charAt(1) == '5' && x1.charAt(1) == '7') {
                    return true;
                }
                if (Math.abs((int) x.charAt(1) - (int) x1.charAt(1)) == 1) {
                    return true;
                }
            }
        }

        if (figure.equals("Horse")) {
            if (Math.abs((int) x.charAt(0) - (int) x1.charAt(0)) == 2 && Math.abs((int) x.charAt(1) - (int) x1.charAt(1)) == 1) {
                return true;
            }
            if (Math.abs((int) x.charAt(1) - (int) x1.charAt(1)) == 2 && Math.abs((int) x.charAt(0) - (int) x1.charAt(0)) == 1) {
                return true;
            }
        }

        if (figure.equals("Bishop")) {
            if (Math.abs((int) x.charAt(0) - (int) x1.charAt(0)) == Math.abs((int) x.charAt(1) - (int) x1.charAt(1))) {
                return true;
            }
        }

        if (figure.equals("Rook")) {
            if (x.charAt(0) == x1.charAt(0) || x.charAt(1) == x1.charAt(1)) {
                return true;
            }
        }

        if (figure.equals("Queen")) {
            if (Math.abs((int) x.charAt(0) - (int) x1.charAt(0)) == Math.abs((int) x.charAt(1) - (int) x1.charAt(1))) {
                return true;
            }
            if (x.charAt(0) == x1.charAt(0) || x.charAt(1) == x1.charAt(1)) {
                return true;
            }

        }

        if (figure.equals("King")) {
            if (Math.abs((int) x.charAt(0) - (int) x1.charAt(0)) <= 1 && Math.abs((int) x.charAt(1) - (int) x1.charAt(1)) <= 1) {
                return true;
            }
        }
        return false;
    }


    // 3 таск - функция, учитывая входную строку, определяет, может ли слово быть завершено
    public static boolean canComplete(String s1, String s2) {
        /* проходим по 2 строке. Проверяем каждый символ с тем, на который указывает s1Iterator
         * если совпадает, значит 1 символ найден и s1Move двигается вперед. */
        int s1Move = 0;
        for (int s2Move = 0; s2Move < s2.length(); s2Move++) {
            if (s1.charAt(s1Move) == s2.charAt(s2Move)) {
                s1Move++;
                if (s1Move == s1.length()) {
                    return true;
                }
            }
        }
        return false;
    }


    //таск 4 - функция, складывающая числа и возвращает произведение его
    //цифр до тех пор, пока ответ не станет длиной всего в 1 цифру
    public static int sumDigProd(int... s) {
        int sum = 0;
        for (int i : s) {
            sum += i;
        }
        while (sum >= 10) {
            sum = multiplication(sum);
        }
        return (sum);
    }
    public static int multiplication(int s) { //произведение цифр числа
        int a = 1;
        while (s > 0) {
            a *= s % 10;
            s /= 10;
        }
        return a;
    }


    //5 таск - функция, выбирающая все слова, имеющие все те же гласные, что и первое слово, включая его
    public static String[] sameVowelGroup(String[] words) {
        Set<Character> vowel = new HashSet<>(); // получаем множество гласных из первого слова, удалив из слова все согласные
        String fWord = words[0].replaceAll("[qwrtpsdfghjklzxcvbnm]", "");
        for (char s : fWord.toCharArray()) {
            vowel.add(s);
        }
        ArrayList<String> result = new ArrayList<>(); // задаем массив результата
        result.add(words[0]);
        for (int i=1; i<words.length; i++) { //получаем множества гласных других слов и добавляем по возможности в массив результата
            Set<Character> curVowel = new HashSet<>();
            String curWord = words[i].replaceAll("[qwrtpsdfghjklzxcvbnm]", "");
            for (char s : curWord.toCharArray()) {
                curVowel.add(s);
            }
            if (vowel.equals(curVowel)){
                result.add(words[i]);
            }
        }
        return result.toArray(new String[]{});
    }


    //6 таск - проверка на действительный номер кредитной карты
    public static boolean validateCard(long num) {
        int str_len = String.valueOf(num).length(); //проверяем что число в нужном диапазоне
        if ( (str_len < 14) || (str_len > 19))
            return false;
        int lastDigit = (int) (num%10); //ищем контрольную цифру
        StringBuilder number = new StringBuilder(String.valueOf(num/10)).reverse();

        int sum = 0; //пробегаемся по числу в обратном порядке, и сразу же складываем найденные цифры (или удвоенные цифры на нечетных позициях)
        for (int i=number.length()-1; i>=0; i--) {
            int doubleInt = number.charAt(i);
            if ((number.length() - i) % 2 != 0) {
                doubleInt *= 2;
                if (doubleInt > 9) {
                    doubleInt = doubleInt % 10 + (doubleInt / 10) % 10;
                }
            }
            sum += doubleInt;
        }
        return (10 - (sum%10)) == lastDigit; //сравниваем число с контрольной цифрой
    }


    //7 таск - строковое представление числа от 0 до 999
    public static String numToEng(int num) {
        String[] ones = {"zero","one","two","three","four","five","six","seven",
                "eight","nine"};
        String[] tens1 = {"ten","eleven","twelve","thirteen","fourteen","fifteen",
                "sixteen","seventeen","eighteen","nineteen"};
        String[] tens2 = {"","twenty","thirty","forty","fifty","sixty","seventy","eighty",
                "ninety"};
        String number = Integer.toString(num);

        switch (number.length()) {
            case 1: //для чисел 0-9
                return ones[num];
            case 2: //для чисел 10-99
                if (num >= 10 && num <= 19) return tens1[num - 10];
                else return tens2[num/10 - 1] + " " + ones[num%10];
            case 3: //для чисел 100-999
                StringBuilder result = new StringBuilder(ones[num/100] + " hundred ");
                number = number.substring(1);
                if (number.charAt(0) == '0') {
                    number = number.substring(1);
                    result.append(ones[Integer.parseInt(number)%10]);
                }
                else {
                    if (Integer.parseInt(number) >= 10 && Integer.parseInt(number) <= 19) {
                        result.append(tens1[Integer.parseInt(number) - 10]);
                    }
                    else{
                        result.append(tens2[Integer.parseInt(number) / 10 - 1]).append(" ").append(ones[Integer.parseInt(number) % 10]);
                    }
                }
                return result.toString();
            default:
                break;
        }
        return "";
    }


    //8 таск - функция, возвращающая безопасный хеш SHA-256 для данной строки
    public static String getSha256Hash(String message) {
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");//шифр байтов в строку
            byte[] hash = digest.digest(message.getBytes());
            for (byte b : hash) { //перевод в хеш
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1){
                    result.append(0);
                }
                result.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }



    // 9 таск -  функция,принимающая строку и возвращает ее с правильным регистром
    // слова and, the, of и in должны быть строчными
    public static String correctTitle(String s) {
        // делим строку на слова
        String[] words = s.toLowerCase(Locale.ROOT).split(" ");
        StringJoiner joiner = new StringJoiner(" ");
        for (String word: words) {
            // капитализуем все слова, не являющиеся служебными
            if (!word.equals("of") && !word.equals("in") && !word.equals("and") && !word.equals("the")) {
                word = word.substring(0,1).toUpperCase(Locale.ROOT) + word.substring(1);
            }
            joiner.add(word);
        }
        return joiner.toString();
    }


    //10 таск - вывод на экран гексагональной решетки для центрированного шестиугольного числа
    private static String hexLattice(int n) {
        int hexCycle = 1;// объявим циклы - это количество кругов решетки
        int currentElements = 1;
        // пытаемся подобрать цикл для данного числа эллементов.
        // Если цикл не удается подобрать - значит число не центрированное шестиугольное
        while (currentElements < n) {
            // число элементов для данного цикла = 1 + 6* сумма(k)по k от 2 до n, где n - цикл.
            currentElements+= 6*hexCycle;
            hexCycle+=1;
        }
        if (currentElements != n) {
            return "invalid";
        }
        // размер - количество строк = количество точек
        final int hexSize = hexCycle* 2 - 1;
        final int hexSizeWithSpaces = hexSize* 2 - 1;
        // массив строк
        String[] hex = new String[hexSize];
        // цикл с середины фигуры до ее начала. Можно рисовать только половину так как фигура зеркальная.
        for (int i = hexCycle - 1; i >= 0; i--) {
            // находим сколько будет символов для этой строки вместе с пробелами
            int rowSizeWithSpaces = (hexCycle + i)*2 - 1;
            // находим какой отступ нужно будет сделать слева и справа для этой строки
            int spaceInLeft = (hexSizeWithSpaces - rowSizeWithSpaces)/2;
            // рисуем строку - повторяем точки определенное количество раз, и ставим между ними пробелы. добавляем слева и срава отступы.
            hex[i] = " ".repeat(spaceInLeft) + "o".repeat(hexCycle + i).replaceAll(".(?=.)", "$0 ") + " ".repeat(spaceInLeft);
            // если строка не на середине, то рисуем зеркальную строку
            if (i <= hexCycle - 1) {
                hex[hexSize - i - 1] = hex[i];
            }
        }
        // преобразуем массив в строку
        StringJoiner joiner = new StringJoiner("\n");
        for (String h: hex) {
            joiner.add(h);
        }
        return joiner.toString();
    }
}