public class Palindrome {
    public static void main(String[] args) {
        String s="java Palindrome madam racecar apple kayak song noon";
        String[] words = s.split(" "); //разбиение строки на подстроки
        for(String word : words){ //проверка каждой подстроки на палиндром
            if (isPalindrome(word)==true) System.out.println(word);
        }
    }

    public static String reverseString(String s) { //s - строка, которая будет перевернута
        char[] array = s.toCharArray();
        String a = ""; //строка a - результат после переворота строки s
        for (int i = array.length - 1; i >= 0; i--) {
            a = a + array[i]; //добавление к строке символов строки s в обратном порядке
        }
        return a;
    }
    public static boolean isPalindrome(String str) {
        String a = reverseString(str); // если перевернуьая строка совпадает с изначальной - палиндром
        return str.equals(a);
    }
}


