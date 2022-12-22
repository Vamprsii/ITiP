import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("---1 task---");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println("---2 task---");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("---3 task---");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("---4 task---");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{}));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));
        System.out.println("---5 task---");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));
        System.out.println("---6 task---");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println("---7 task---");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println("---8 task---");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));
        System.out.println("---9 task---");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 7 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("---10 task---");
        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
    }

    public static int bell(int s) { //число Белла (массив из n элементов может быть разбит на непустые подмножества
        int[] rows = new int[s];
        int prev;
        int cur;
        rows[0] = 1;
        for (int i = 1; i < s; ++i) {
            prev = rows[0];
            rows[0] = rows[i - 1];
            for (int j = 1; j <= i; ++j) {
                cur = rows[j];
                rows[j] = rows[j - 1] + prev;
                prev = cur;
            }
        }
        return rows[s - 1];
    }


    public static String translateWord(String word) { // Слово
        if (word.length() == 0) {
            return "";
        }

        char firstChar = word.charAt(0);
        if (isVowel(firstChar, false)) {
            return word + "yay";
        }

        int index = 1;
        for (; index < word.length(); ++index)
            if (isVowel(word.charAt(index), true)) {
                break;
            }

        if (Character.isUpperCase(firstChar)) {
            if (word.length() == index) {
                return word + "ay";
            }
            return Character.toUpperCase(word.charAt(index)) + word.substring(index + 1)
                    + Character.toLowerCase(firstChar) + word.substring(1, index)
                    + "ay";
        }

        return word.substring(index) + word.substring(0, index) + "ay";
    }

    public static String translateSentence(String sentence) {
        StringBuilder sentenceBuilder = new StringBuilder();

        int start = 0;
        int index = 0;

        for (; index < sentence.length(); ++index) {
            char c = sentence.charAt(index);
            if (!Character.isLetter(c)) {
                sentenceBuilder.append(translateWord(sentence.substring(start, index)));
                sentenceBuilder.append(c);
                start = index + 1;
            }
        }
        if (index - start > 0) {
            sentenceBuilder.append(translateWord(sentence.substring(start, index)));
        }
        return sentenceBuilder.toString();
    }

    private static boolean isVowel(char c, boolean isYVowel) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
            case 'y', 'Y' -> isYVowel;
            default -> false;
        };
    }


    private static boolean validColor(String s) {
        Integer r = null;
        Integer g = null;
        Integer b = null;
        Double a = null;
        // регулярное выражение, которое принимает целочисленные числа и дроби
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(s);
        //по порядку находим значения r,g,b,a в строке
        while (matcher.find()) {
            try {
                if (r == null) {
                    r = Integer.parseInt(matcher.group());
                } else if (g == null) {
                    g = Integer.parseInt(matcher.group());
                } else if (b == null) {
                    b = Integer.parseInt(matcher.group());
                } else if (a == null) {
                    a = Double.parseDouble(matcher.group());
                }
            } catch (Exception e) {
                return false;
            }
        }
        // проверяем, что все значения соответствуют формату
        if (r == null || r > 255 || r < 0) {
            return false;
        }
        if (g == null || g > 255 || g < 0) {
            return false;
        }
        if (b == null || b > 255 || b < 0) {
            return false;
        }
        if (s.contains("rgba")) {
            return a != null && a <= 1 && a >= 0;
        } else {
            return a == null && s.contains("rgb");
        }
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {

        if (!url.contains("?")) // Проверяем, содержит ли адрес вопросительный знак
            return url;

        String[] result = url.split("\\?"); // инициализируем массив с именем "result", который будет содержать два элемента в виде строк.
// Первый элемент - это первая половина входящего URL-адреса, вплоть до вопросительного знака.
// И второй элемент - это вторая половина входящего URL-адреса после вопросительного знака
        StringBuilder res = new StringBuilder(); // Создаём экземпляр res, содержащий первую половину адреса
        res.append(result[0]).append("?");
        String[] params = result[1].split("&"); // Массив, содержащий каждый параметр, содержащийся во второй половине входящего URL-адреса, который разбивается амперсандом
        HashMap<String, String> map = new HashMap<>(); // Создаём хэш-карту, содержащую строки как ключи, так и значения

        for (String i : params) {
            String[] val = i.split("=");
            map.put(val[0], val[1]);
        }

        HashSet<String> strip = new HashSet<>();

        if(paramsToStrip != null) {
            strip.addAll(Arrays.asList(paramsToStrip));
        }

        res.append("?");
        int k = 1;

        for(String key : map.keySet()) {
            if(!strip.contains(key))
            {
                if(k > 1)
                    res.append("&");
                res.append(key).append("=").append(map.get(key));
                k++;
            }
        }

        return res.toString();
    }


    public static String[] getHashTags(String s) {
        s = s.replaceAll(",", "");
        String[] arr = s.split(" ");
        int maxNum = 0;
        int pos = 0;
        StringBuilder longestWord = new StringBuilder();
        StringBuilder result = new StringBuilder();

        if(arr.length < 3){
            for(String word:arr){
                result.append("#").append(word.toLowerCase()).append(",");
            }
        }
        else{
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < arr.length; j++){
                    if (arr[j].length() != maxNum) {
                        if(arr[j].length() >= maxNum){
                            maxNum = arr[j].length();
                            longestWord = new StringBuilder(arr[j].toLowerCase());
                            pos = j;
                        }
                    }
                }
                arr[pos] = " ";
                result.append("#").append(longestWord).append(",");
                maxNum = 0;

            }
        }
        return result.toString().split(",");
    }


    public static int ulam(int s) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        int i, j;
        for (i = 3, j = 2; j < s; i++) {
            int count = 0;
            for (int a = 0; a < arr.size() - 1; a++) {
                for (int l = a + 1; l < arr.size(); l++) {
                    if (arr.get(a) + arr.get(l) == i)
                        count++;
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }
            if (count == 1) {
                arr.add(i);
                j++;
            }
        }
        return i - 1;
    }


    public static StringBuilder longestNonrepeatingSubstring(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder current = new StringBuilder();
        for(int i = 0; i < s.length(); ++i){
            for(int j = i; j < s.length(); ++j){
                if (!current.toString().contains(String.valueOf(s.charAt(j)))){
                    current.append(s.charAt(j));
                }
                else{
                    if(current.length() > result.length()) {
                        result = current;
                    }
                    current = new StringBuilder();
                    j = s.length();
                }
            }
        }
        return result;
    }


    public static StringBuilder convertToRoman(int s) {
        StringBuilder result = new StringBuilder();
        while(s != 0){
            if(s >= 1000){
                s -= 1000;
                result.append("M");
            }
            else if(s >= 900){
                s -= 900;
                result.append("CM");
            }
            else if(s >= 500){
                s -= 500;
                result.append("D");
            }
            else if(s >= 400){
                s -= 400;
                result.append("CD");
            }
            else if(s >= 100){
                s -= 100;
                result.append("C");
            }
            else if(s >= 90){
                s -= 90;
                result.append("XC");
            }
            else if(s >= 50){
                s -= 50;
                result.append("L");
            }
            else if(s >= 40){
                s -= 40;
                result.append("XL");
            }
            else if(s >= 10){
                s -= 10;
                result.append("X");
            }
            else if(s >= 9){
                s -= 9;
                result.append("IX");
            }
            else if(s >= 5){
                s -= 5;
                result.append("V");
            }
            else if(s >= 4){
                s -= 4;
                result.append("IV");
            }
            else if(s > 0){
                s -= 1;
                result.append("I");
            }
        }
        return result;
    }


    private static boolean formula(String s) {
        // делим равенства на конкретные выражения
        String[] expressions = s.split("=");

        // регулярное выражение определяющее числа
        Pattern patternDigit = Pattern.compile("\\d+");
        Matcher matcherDigit = patternDigit.matcher("");
        // регулярное выражение определяющее операторы
        Pattern patternOperators = Pattern.compile("[*/+-]");
        Matcher matcherOperators = patternOperators.matcher("");

        // текущий результат при расчете выражения
        Double lastResult = null;

        for (String expr : expressions) {
            ArrayList<Integer> digits = new ArrayList<>();
            ArrayList<Character> operators = new ArrayList<>();
            matcherOperators.reset(expr);
            matcherDigit.reset(expr);
            // получаем массив цифр и массив операторов для части уравнения
            while (matcherDigit.find()) {
                digits.add(Integer.parseInt(matcherDigit.group()));
            }
            while (matcherOperators.find()) {
                operators.add(matcherOperators.group().charAt(0));
            }
            // между n числами должно быть n-1 операторов. Если это не так - возвращаем ложь.
            if (digits.size() != operators.size() + 1) {
                return false;
            }

            // по одному применяем оператор к двум соседним цифрам и записываем результат в lastResult
            double currentDouble = digits.get(0);
            for (int i = 0; i < operators.size(); i++) {
                int newInt = digits.get(i + 1);
                switch (operators.get(i)) {
                    case '*': {
                        currentDouble *= newInt;
                        break;
                    }
                    case '/': {
                        currentDouble /= newInt;
                        break;
                    }
                    case '+': {
                        currentDouble += newInt;
                        break;
                    }
                    default: {
                        currentDouble -= newInt;
                        break;
                    }
                }
            }
            // если хоть какая-то часть уравнения не равна предыдущей, то фолс
            if (lastResult != null && currentDouble != lastResult) {
                return false;
            }
            lastResult = currentDouble;
        }
        return true;
    }



    private static boolean palindromeDescendant(int number) {
        if (number < 10) {
            return true;
        }

        while (number > 10) {
            // проверяем является ли текущая строка палиндромом
            String numberStr = "" + number;
            String reversedStr = new StringBuilder(numberStr).reverse().toString();
            if (numberStr.equals(reversedStr)) {
                return true;
            }
            // если это не палиндром, и число нечетное, то потомков создать нельзя
            if (numberStr.length() % 2 != 0) {
                return false;
            }
            // создаем потомка
            StringBuilder newStrBuilder = new StringBuilder();
            for (int i = 0; i <= numberStr.length() - 2; i += 2) {
                newStrBuilder.append(Integer.parseInt(numberStr.substring(i, i + 1)) + Integer.parseInt(numberStr.substring(i + 1, i + 2)));
            }
            number = Integer.parseInt(newStrBuilder.toString());
        }
        return false;
    }
}