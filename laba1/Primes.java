public class Primes {
    public static void main(String[] args) {

        for(int i=2; i<100; i++){
            boolean isPrime=true;

            for(int j=2; j<i; j++){ // функция проверяет, делится ли число без остатка
                if(i%j==0) {  // если число делится без остатка, значит, оно не является простым
                    isPrime = false; //Если полностью делится на другое число, мы возвращаем false и цикл прерывается
                    break;
                }
            }
            if(isPrime) {
                System.out.print(i+" ");
            }
        }
    }
}

