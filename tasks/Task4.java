import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("---1 task---");
        essay(10, 7, "hello my name is Bessie and this is my essay");
        System.out.println("---2 task---");
        System.out.println(Arrays.toString(split("()()()")));
        System.out.println(Arrays.toString(split("((()))")));
        System.out.println(Arrays.toString(split("((()))(())()()(()())")));
        System.out.println(Arrays.toString(split("((())())(()(()()))")));
        System.out.println("---3 task---");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor"));
        System.out.println("---4 task---");
        System.out.println(overtime(9, 17, 30, 1.5));
        System.out.println(overtime(16, 18, 30, 1.8));
        System.out.println(overtime(13.25, 15, 30, 1.5));
        System.out.println("---5 task---");
        System.out.println(bmi("205 pounds", "73 inches"));
        System.out.println(bmi("55 kilos", "1.63 meters"));
        System.out.println(bmi("154 pounds", "2 meters"));
        System.out.println("---6 task---");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println("---7 task---");
        System.out.println(toStartShorthand("abbccc"));
        System.out.println(toStartShorthand("77777geff"));
        System.out.println(toStartShorthand("abc"));
        System.out.println(toStartShorthand(""));
        System.out.println("---8 task---");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println("---9 task---");
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println("---10 task---");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
    }

    //1 - ?????????????????????????? ???????????? ?? ???????????? ???? ?????????? ??????, ?????????? ?? ???????????? ???????????? ???????? ???? ???????????? k ????????????????
    public static void essay(int n, int k, String s) {
        String[] strings = s.split(" "); // ?????????? ???????????? ???? ??????????
        StringJoiner currentString = new StringJoiner(" "); // ???????????? ?????? ?????????????? ????????????
        for (String string : strings) {
            if (string.length() + currentString.length() > k) { // ???????? ?? ???????????? ???????????? ???????????????? ?????????? - ?????????????????? ????????????.
                System.out.println(currentString);
                currentString = new StringJoiner(" ");
            }
            currentString.add(string);
        }
        if (currentString.length() != 0) {
            System.out.println(currentString);
        }
    }

    //2 - ??????????????, ?????????????? ???????????????????? ???????????? ?? ?????????????? ????????????
    public static String[] split(String s) {  /*?????????????????? ?????????????? ?? ?????????????? ???? 1
        ???????? ?? ???????????????? ?????????????????? ???????????? ( , ?? ?????????? ), ???? ???????????? ???????????? ?????????????????? ?? ???????????? ( ?????????? ????????????
        ???????? ?????????????? ??????????????, ???? ???????? ?????????????? ???????????? ??????????????????*/
        Queue<Character> cluster = new LinkedList<>();
        int a = 0;
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (cluster.isEmpty() && i != 0) { // ???????? ?? ???????????????? ???????????? ?????? ???????????????? - ?????????????????? ???????????? ???? ????????????????
                strings.add(s.substring(a, i));
                a=i;
            }
            if (cluster.peek() != null && cluster.peek() == '(' && currentChar == ')') {
                cluster.poll();
            }
            else {
                cluster.add(currentChar);
            }
        }
        strings.add(s.substring(a));
        return strings.toArray(new String[]{});
    }


    //3.1 - ???????????????????????????????? ???????????? ???????????????????????? ???? snake_case ?? camel_case
    public static String toCamelCase(String s){
        StringBuilder s1 = new StringBuilder();
        if (s.charAt(0) != '_') {
            s1.append(s.charAt(0));
        }
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i) != '_' && s.charAt(i-1) != '_'){
                s1.append(s.charAt(i));
            }
            if (s.charAt(i-1) == '_') {
                s1.append((char) ((int) s.charAt(i) - 32)); //???? ASCII ?????? ???????????????? ??????????
            }
        }
        return (s1.toString());
    }
    //3.2 - ???????????????????????????????? ???????????? ???????????????????????? ???? camel_case ?? snake_case
    public static String toSnakeCase(String s) {
        StringBuilder new_str = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            if ((int)s.charAt(i) >= 65 && (int)s.charAt(i) <= 90) { // 65-90 -- A-Z ASCII
                new_str.append("_").append((char) ((int) s.charAt(i) + 32));
            }
            else {
                new_str.append(s.charAt(i));
            }
        }
        return (new_str.toString());
    }

    //4 - ???????????????????? ???????????? ?????????????????? ?? ???????????????????????? ?? ?????????????????????????? ????????????
    public static String overtime(double start, double finish, double payment, double multiplier){
       double pay = 0;
        if (start<=17 && finish<=17) { //???????? ???????????? ???????????? ???? 17 ?? ?????????? ???? 17 (????????????????????????)
            pay = (finish-start)*payment; //?????????????? ???????????????????? ?????????? ?? ???? ????????????
        }
        if (start>17 && finish>17) { //???????? ???????????? ???????????? ?????????? 17 ?? ?????????? ?????????? 17
            pay = (finish-start)*payment*multiplier; //?????????????????????????? ???????????????? ???? ????????????????????????
        }
        if (start<=17 && finish>17) { //???????? ???????????? ???????????? ???? 17 (????????????????????????) ?? ?????????? ?????????? 17
            pay = (17-start)*payment + (finish-17)*payment*multiplier; //?????????????? ???????????????? ???????????????? ?? ?????????????? ???? ???????????????????????? ?? ????????????????????
        }
        if (start > finish){ //???????????? ???????????????? ?????? ?? ???????????? ???? ?????????? ???????? ???????????? ??????????
            return ("Error"); //?????????????? ?????????????? ????????????
        }
        return String.format("$ %.2f", pay); //?? ?????????? ?????????????????? ???????? ???????????? ?? ?????????????????? ???????????????? ???????????????????? ???????????? ?????????? ??????????????
    }


    //5 - ???????????? ?????????????? ?????????? ????????
    public static String bmi(String w, String h){
        StringBuilder weight = new StringBuilder();
        StringBuilder height = new StringBuilder();
        double kilos = 1;
        double meters = 1;
        for (int i=0; i<w.indexOf(" ")+2; i++) {
            if (Character.isDigit(w.charAt(i)) || w.charAt(i) == '.'){ //???????????????? ?????????? ???? ??????????
                weight.append(w.charAt(i));
            }
            if (w.charAt(i) == ' ' && w.charAt(i+1) == 'p') { //???????? ???????????? ?????????? ?????????? ?? - ?????????????????? ?? ????????????????????
                kilos = 0.454;
            }
        }
        for (int i=0; i<h.indexOf(" ")+2; i++) {
            if (Character.isDigit(h.charAt(i)) || h.charAt(i) == '.') {
                height.append(h.charAt(i));
            }
            if (h.charAt(i) == ' ' && h.charAt(i+1) == 'i') { //???????? ???????????? ?????????? ?????????? i - ?????????????????? ?? ??????????
                meters = 1/39.37;
            }
        }
        double bmi = kilos * Double.parseDouble(weight.toString())/(meters * Double.parseDouble(height.toString()) * meters * Double.parseDouble(height.toString()));
        if (bmi < 18.5) {
            return (String.format("%.1f", bmi) + " Underweight");
        }
        if (bmi >= 18.5 && bmi < 25) {
            return (String.format("%.1f", bmi) + " Normal weight");
        }
        else {
            return (String.format("%.1f", bmi) + " Overweight");
        }
    }


    //6 - ?????????????????????????????????? ?????????????????????? (???????????????????? ??????, ?????????????? ?????????? ???????????????? ?????????? ?????????? ?????????? ???????????????? 1 ??????????
    public static int bugger(int s) {
        if (s < 10) { //???????? ?????????????? ?????????? < 10, ???? ?????????? ?????????????????? ????????????????
            return 0;
        }
        int multiplies = 1;
        for (char c : ("" + s).toCharArray()) { // ???????????????? ???????????????????????? ???????? ??????????
            multiplies *= Integer.parseInt(String.valueOf(c));
        }
        return 1 + bugger(multiplies);// ?????????????????? ???????????????? ?????? ?????????????????????? ????????????????????????
    }



    //7 -  ????????????, ?? ?????????????? ???????????????????? ???????????????? ???????????????????? ???? ?????????????????? ???????? "a*n"
    public static StringBuilder toStartShorthand(String s){
        StringBuilder a = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            int n = 1; //???????????????????? ????????????????????
            for (int j=i+1; j<s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) { //?????????????????? ?????????? ???? ???????????? ??????????????????????
                    n++;
                    i++;
                }
            }
            if (n>1){ // ???????? ???????????????????? ???????? > 1 ???? ?????????? ???????????????????? ????????????????????
                a.append(s.charAt(i)).append("*").append(n);
            }
            else{
                a.append(s.charAt(i));
            }
        }
        return (a);
    }


    //8 - ?????????? true, ???????? ?????????????? ?? ?????????????????? ?????????? ?????????????????????? ???????????????????? ?? ???????? ?? ???????????????????? ??????????????
    public static boolean doesRhyme(String s1, String s2){
        String a = s1.toLowerCase(); //????????????????????????????????  ?????? ?????????? ?? ???????????? ??????????????
        String b = s2.toLowerCase();
        StringBuilder a1 = new StringBuilder(" ");
        StringBuilder b1 = new StringBuilder(" ");
        for (int i=a.length()-1; i>0; i--) {
            if (a.charAt(i) != ' ') {//???????????????? ???????????????? ?????????????? ?? ?????????? ?? ???????????? ????????????
                if (a.charAt(i) == 'a' || a.charAt(i) == 'u' || a.charAt(i) == 'e' || a.charAt(i) == 'o' || a.charAt(i) == 'i')
                    a1.append(a.charAt(i));
            }
            else break;
        }
        for (int i=b.length()-1; i>0; i--) {
            if (b.charAt(i) != ' ') {
                if (b.charAt(i) == 'a' || b.charAt(i) == 'u' || b.charAt(i) == 'e' || b.charAt(i) == 'o' || b.charAt(i) == 'i')
                    b1.append(b.charAt(i));
            }
            else break;
        }
        return (a1.toString()).equals(b1.toString());
    }


    //9 - ?????????? true, ???????? ???????? ?????????? ??????????, ?????????????? ?????????? 3 ???????? ?????????????????????? ?? num1 ?? 2 ???????? ?????????????????????? ?? num2
    public static boolean trouble(long num1, long num2) {
        String n1 = String.valueOf(num1);
        char numeral = 'n'; //??????????, ?????????????? ???? ?????????? ?????????????????? ???? ????????????????????
        for (int i=0; i<n1.length()-2; i++) {
            if (n1.charAt(i) == n1.charAt(i+1) && n1.charAt(i+1) == n1.charAt(i+2)) { //???????? ???? ?????????????? ?????????? ?? ??????????????, ?????????? ???? ?????? ?????????????????????? 3 ????????
                numeral = n1.charAt(i); //?????????????????????? ???????????????? ???????????????????????????? ??????????
                break;
            }
        }
        String n2 = String.valueOf(num2);
        if (numeral != 'n') {
            for (int i=0; i<n2.length()-1; i++) {
                if (n2.charAt(i) == n2.charAt(i+1) && n2.charAt(i) == numeral)
                    return true;
            }
        }
        return false;
    }


    //10 - ???????????????????? ???????????????????? ???????????????????? ????????????????, ?????????????????????????? ?? ???????????????? ????????????
    public static int countUniqueBooks(String books, char bookend) { //?????????? ??????-???? ???????????????????? ???????????????? ?????????? ?????????? ???????????? bookend
        Set<Character> bSet = new HashSet<>(); //?????????? ???????????????? ???????????? ???????????????????? ??????????????
        boolean between = false;
        for (char j :books.toCharArray()) {
            if (j == bookend) { // ???????? ?????????? ?????????????? - ?????????????????? ???????????? ?? ??????. ?????????? ???? ??????????????????.
                between = !between;
            }
            else {
                if (between) {
                    bSet.add(j);
                }
            }
        }
        return bSet.size();
    }
}