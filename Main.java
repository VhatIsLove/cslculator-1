import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите: (число) (действие) (число)");
        String expression = scanner.nextLine();
        System.out.println(parse (expression));
    }
     public static String parse (String expression) throws Exception {
        int num1;
        int num2;
        String operation;
        String resultat;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) {
            throw new Exception("Колличество операндов должно ровняться двум!");
        }
        operation = detectOperation(expression);
        if (operation == null) {
            throw new Exception("Некорректная математическая операция!");
        }
        // Проверка на римские числа
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.converterToArabian(operands[0]);
            num2 = Roman.converterToArabian(operands[1]);
            isRoman = true;
        }
         // Проверка на арабские числа
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // Разная система исчеслений
        else {
            throw new Exception("Числа должны быть в одной системе счисления!");
        }
        if (num1 > 10 || num2 > 10){
            throw new Exception("Вводимые числа должны быть от 1 до 10!");
        }
        int arab = calc (num1, num2, operation);
        if (isRoman){
            if (arab <= 0){
                throw new Exception("Римсоке число не может быть отрицательным или равным 0!");
            }
            // Конвертируем из арабского в римское
            resultat = Roman.convertToRoman(arab);
        }else{
            resultat = String.valueOf(arab);
        }
        return resultat;
    }
    static String detectOperation (String expression){
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String operation){
        if (operation.equals("+")) return a+b;
        else if (operation.equals("-")) return a-b;
        else if (operation.equals("*")) return a*b;
        else  return a/b;
    }

}
class Roman {
    static String [] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX","XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
            "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
            "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
            "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI",
            "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
            "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
             "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val){
        for(int i = 0; i < romanArray.length; i++){
            if (val.equals(romanArray[i])){
                return true;
            }
        }
        return false;
    }
    public static int converterToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String convertToRoman(int arabian){
        return romanArray[arabian];
    }
}