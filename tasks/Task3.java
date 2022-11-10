import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        //Результаты для каждого таска
        System.out.println(solutions(1, 0, 0)); //1 таск
        System.out.println(findZip("all zip files are zipped")); //2 таск
        System.out.println(checkPerfect(12)); //3 таск
        System.out.println(flipEndChars("Cat, dog, and mouse."));  //4 таск
        System.out.println(isValidHexCode("#EAECEE"));//5 таск
        System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7})); //6 таск
        System.out.println(isKaprekar(297));//7 таск
        System.out.println(longestZero("100100100"));//8 таск
        System.out.println(nextPrime(24));//9 таск
        System.out.println(rightTriangle(3, 4, 5));//10 таск
    }

    // метод, возвращающий число решений квадратного уравнения
    public static int solutions(int a, int b, int c){
        double D = Math.pow(b,2) - 4*a*c;
        if (D>0){
           return (2);
        }
        if (D==0){
            return (1);
        }
        else{
            return (0);
        }
    }

    //функция, которая возвращает позицию второго вхождения "zip"
    public static int findZip(String s) {
        int first = s.indexOf("zip"); //нахождение позиции первого вхождения zip
        String noFirst = s.substring(first+3); //убираем из строки все до zip, включая его самого
        int a = noFirst.indexOf("zip"); //находим вхождение в уже "обрезанной" строке
        if (a == -1){ //если zip не был найден
            return -1;
        }
        else {
            return (first + a); //первое вхождение + второе вхождение в обрезанную строку + длина zip
        }
    }

    //проверка числа на совершенное (сумма цифр = числу)
    public static boolean checkPerfect(int number){
        int sum = 0;
        for (int i = 1; i < number; i++) { //перебираем все множители
            if (number % i == 0) {
                sum += i;
            }
        }
        if (number == sum){
            return true;
        }
        else {
            return false;
        }
    }

    //замена в строке первого символа на последний и наоборот
    public static String flipEndChars(String s){
        if (s.length() < 2) { //если длина строки меньше двух, возращаем "несовместимо"
            return "incompatible";
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) { //если первый и последний символы совпадают, возращаем "два-это пара.".
            return "Two's a pair";
        }
        else {
            return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0); //меняем местами
        }
    }

    //определение, является ли строка допустимым шестнадцатеричным кодом
    public static boolean isValidHexCode(String s) {
        if (s.charAt(0) != '#' && s.length() != 7) { //если в начале не стоит # и длина вместе с ней не равна 6, то сразу выводим false
            return false;
        }
        else if (!s.substring(1,s.length()).matches("[A-Fa-f0-9]{6}")){ //рассматриваем подстроку без #
          return false; //идем от обратного, поэтому возращается false
        }
        else{
            return true;
        }
    }

    //функция, которая возвращает true, если два массива имеют одинаковое количество уникальных элементов, и false в противном случае
    public static boolean same(int [] arr1, int [] arr2) {


        // проверяем, что у множеств элементов этих массивов одинаковые размеры
        return remove(arr1).size() == remove(arr2).size();
    }
     public static List<Integer> remove(int[] arr) {
        List<Integer> newArr = new ArrayList<Integer>();
        for (int number : arr) {
            if (!(newArr.contains(number))) {
                newArr.add(number);
            }
        }
        return newArr;
    }

    //проверка на число Капрекара
    public static boolean isKaprekar(int s) {
      int a = s*s;
      String sStr = Integer.toString(a);
      int sLength = sStr.length();
      if (sLength == 1){
          return a == s; //если число состоит из 1 символа (в левой стороне числа не будет - в этом случае запишем 0)
      }
      else{
          int left = Integer.parseInt(sStr.substring(0, sLength/2)); //sLength/2 - разделитель нового числа надвое
          int right = Integer.parseInt(sStr.substring(sLength/2+1));
          return left + right == s;  //если сумма левой и правой частей квадрата числа равна самому числу - то это число капрекара
      }
    }

    //возвращение самой длинной последовательности нулей в строке
    private static String longestZero(String s) {
        int max = 0; //максимальная найденная последовательность нулей
        int current = 0; //текущая последовательность нулей
        for (char a: s.toCharArray()) {
            if (a != '0') { //если сейчас не 0, то найденное количество нулей сбрасывается
                current = 0;
            } else {
                current+=1;
            }
            if (current > max) { //если текущее количество нулей больше максимального - перезаписываем
                max = current;
            }
        }
        return "0".repeat(max); //возвращаем 0, повторяемое найденное количество раз
    }

    //возвращение следующего простого числа после заданного
    public static int nextPrime(int s){
        while (true) { //ищем среди заданного числа и следующих за ним простое
            if (isPrime(s)) {
                return s;
            }
            s++;
        }
    }
    public static boolean isPrime(int number) {
        int n = 0;
        for (int i = 2; i < number; i++) { //проверяем что на промежутке от 2 до числа нет его множителей
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    //являются ли три числа рёбрами прямоугольного треугольника
    public static boolean rightTriangle(int x, int y, int z) {
        if (x*x == y*y + z*z || y*y == x*x + z*z || z*z == x*x + y*y) {
            return true;
        }
        else {
            return false;
        }
    }
}