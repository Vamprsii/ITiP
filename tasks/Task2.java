public class Task2 {
    public static void main(String[] args) {
        int[] task2 = {10, 4, 1, 4, -10, -50, 32, 21};
        int[] task3 = {1, 2, 3, 4};
        //Результаты для каждого таска
        System.out.println(repeat("mice", 5)); //1 таск
        System.out.println(differenceMaxMin(task2)); //2 таск
        System.out.println(isAvgWhole(task3)); //3 таск
        cumulativeSum(new int [] {3, 3, -2, 408, 3, 3}); //4 таск
        System.out.println();
        System.out.println(getDecimalPlaces("43.20"));//5 таск
        System.out.println(Fibonacci(12)); //6 таск
        System.out.println(isValid("853a7"));//7 таск
        System.out.println(isStrangePair("sparkling", "groups"));//8 таск
        System.out.println(isPrefix("automation", "auto-"));//9.1 таск
        System.out.println(isSuffix("vocation","-logy"));//9.2 таск
        System.out.println(boxSeq(2));//10 таск
    }

    // функция, повторяющая каждый символ в строке n раз.
    public static String repeat(String a, int n) {
        String s = "";
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < n; j++)
                s += a.charAt(i);
        }
        return (s);
    }

    ////возвращение разницы между максимальным и минимальным числом из массива чисел
    public static Integer differenceMaxMin(int[] task2) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < task2.length; i++) {
            if (task2[i] > max) {
                max = task2[i];
            } else {
                if (task2[i] < min) {
                    min = task2[i];
                }
            }
        }
        return max - min;
    }

    //функция, проверяющая является ли среднее значение целым
    public static boolean isAvgWhole(int[] task3) {
        int sum = 0;
        for (int i = 0; i < task3.length; i++) {
            sum += task3[i];
        }
        return (sum % task3.length) == 0;
    }

    //возвращение массива, в котором каждое число - сумма самого себя и всех предыдущих
    public static void cumulativeSum(int [] s) {
        for (int i=0; i < s.length; i++) {
            int sum = 0;
            int[] c = new int[s.length];
            for (int j=0; j<=i; j++)
                sum += s[j];
            c[i] = sum;
            System.out.printf(c[i] + " ");
        }
    }

    //функция возвращения количества знаков после запятой
    public static int getDecimalPlaces(String s) {
        int a = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '.') a = i;
        }
        if (a == 0)
            return 0;
        else
            return (s.length()-(a+1));
    }

    //функция возвращения соответсвующего числа Фибоначчи
    public static int Fibonacci(int n) {
        int[] s = new int[n+1];
        s[0] = s[1] = 1;
        for (int i=2; i<=n; i++) {
            s[i] = s[i-1] + s[i-2];
        }
        return (s[n]);
    }

    //функция проверяет, является ли почтый индекс действительным
    public static boolean isValid(String s) {
        int a = 0;
        for (int i=0; i<s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false || s.charAt(i) == ' ' || s.length() != 5 ) a++;
        }
        if (a == 0)
            return true;
        else
            return false;
    }

    //функция проверки пары строк на "странную" пару
    public static boolean isStrangePair(String first,String second){
        if(first.isEmpty()){
            return false;
        }
        else if(first.indexOf(0)==second.indexOf(second.length()-1)){
            if(second.indexOf(0)==first.indexOf(second.length()-1)){
                return true;
            }
            else return false;
        }
        else return false;
    }

    //функция проверки на начало строки с префиксного аргумента
        public static boolean isPrefix (String word, String prefix) {
        int s = 0;
        for (int i=0; i<prefix.length()-1; i++) {
            if (word.charAt(i) == prefix.charAt(i)) s++;
        }
        if (s == prefix.length()-1)
            return true;
        else
            return false;
    }

    //функция проверки на конец строки аргументом суффикса
    public static boolean isSuffix (String word, String suffix) {
        int s = 0;
        for (int i=1; i<suffix.length(); i++) {
            if (word.charAt(i + word.length() - suffix.length()) == suffix.charAt(i)) s++;
        }
        if (s == suffix.length()-1)
            return true;
        else
            return false;
    }

    //функция, принимающая число (шаг) в качестве аргумента и возвращающая количество полей на этом шаге последовательности
    public static int boxSeq(int n){
        int count=0;
        if (n==0)
            return 0;
        for (int i = 1; i <=n; i++) {
            if(i%2!=0) count+=3;
            else count--;
        }
        return count;
    }
}