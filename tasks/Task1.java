public class Task1 {
    public static void main(String[] args) {
        int [] task9 = {1, 5, 9}; //массив для 9 таска
        //Результаты для каждого таска
        System.out.println(remainder(1, 3)); //1 таск
        System.out.println(triArea(3, 2)); //2 таск
        System.out.println(animals(2, 3, 5)); //3 таск
        System.out.println(profitableGamble(0.2, 50, 9)); //4 таск
        System.out.println(operation(24, 15, 9)); //5 таск
        System.out.println(ctoa('A')); //6 таск
        System.out.println(addUpTo(3)); //7 таск
        System.out.println(nextEdge(8, 10)); //8 таск
        System.out.println(sumOfCubes(task9)); //9 таск
        System.out.println(abcmath(42, 5, 10)); //10 таск
    }

    //1 таск - определение остатка от деления
    public static int remainder(int a, int b) {

        return (a%b);
    }

    //2 таск - вычисление площади треугольника
    public static int triArea(int a, int b) {

        return (a*b/2);
    }

    //3 таск - вычисление общего количества ног всех животных
    public static int animals(int a, int b, int c) {

        return (2*a+4*b+4*c);
    }

    //4 таск - проверка заданного условия
    public static boolean profitableGamble(double prob, double prize, double pay) {
        if (prob*prize > pay)
            return true;
        else
            return false;
    }

    //5 таск - определение математической операции по трем заданным числам, первое из которых - результат вычислений
    public static String operation(int N, int a, int b) {
        if (a+b == N)
            return ("added");
        if (a-b == N)
            return ("subtracted");
        if (a*b == N)
            return ("multiplied");
        if (a/b == N)
            return ("divided");
        else
            return ("none");
    }

    //6 таск -  возвращение значения ASCII переданного символа
    public static int ctoa(char a) {
        return ((int)a);
    }

    //7 таск - возвращение суммы всех чисел до введенного числа и включая его
    public static int addUpTo(int a) {
        int sum = 0;
        for (int i = 1; i <= a; i++) {
            sum += i;
        }
        return (sum);
    }

    //8 таск -  нахождение максимального значения третьего ребра треугольника, где длины сторон являются целыми числами
    public static int nextEdge(int a, int b) {
        return (a+b-1);
    }

    //9 таск - функция, принимающая массив чисел и возвращая сумму его кубов
    public static int sumOfCubes(int[] s) {
        int sum = 0;
        for (int i=0; i<s.length; i++) {
            sum += Math.pow(s[i], 3);
        }
        return (sum);
    }

    //10 таск - проверка делимости числа А, прибавленного к себе В раз, на С
    public static boolean abcmath(int a, int b, int c) {
        if ((Math.pow(2, b) * a) % c == 0)
            return true;
        else
            return false;
    }
}