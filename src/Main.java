import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в формате \"a\" + \"b\": ");
        String input = scanner.nextLine();

        try {
            String result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String input) {
        String[] space = input.split(" ");
        if (space.length != 3) {
            throw new IllegalArgumentException("Должно быть 2 операнда или корректный ввод");
        }
        String operand1 = space[0];
        String operand = space[1];
        String operand2 = space[2];

        if (!operand1.startsWith("\"") || !operand1.endsWith("\"")) {
            throw new IllegalArgumentException("Операнд должен быть вписан в \"\"");
        }
        operand1 = operand1.substring(1, operand1.length() - 1);


        if (!operand1.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Первый операнд должен быть строкой");
        }

        String result;
        switch (operand) {
            case "+":
                if (!operand2.startsWith("\"") || !operand2.endsWith("\"")) {
                    throw new IllegalArgumentException("Операнд должен быть вписан в \"\"");
                }
                operand2 = operand2.substring(1, operand2.length() - 1);
                result = addStrings(operand1, operand2);
                break;
            case "-":
                if (!operand2.startsWith("\"") || !operand2.endsWith("\"")) {
                    throw new IllegalArgumentException("Операнд должен быть вписан в \"\"");
                }
                operand2 = operand2.substring(1, operand2.length() - 1);
                result = minusStrings(operand1, operand2);
                break;
            case "*":
                if (!operand2.matches("[1-9|10]")) {
                    throw new IllegalArgumentException("Числа не должны быть меньше 1 и привышать 10");
                }
                result = multiplyStrings(operand1, Integer.parseInt(operand2));
                break;
            case "/":
                result = divideStrings(operand1, Integer.parseInt(operand2));
                break;
            default:
                throw new IllegalArgumentException("Вы ввели некорректный оператор вычисления");
        }
        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }
        return result;
    }


    public static String addStrings(String str1, String str2) {
        String result = str1.concat(str2);
        return result;
    }

    public static String minusStrings(String str1, String str2) {
        String result = str1.replace(str2, "");
        return result;
    }

    public static String multiplyStrings(String str1, int str2) {
        String result = str1.repeat(str2);
        return result;
    }

    public static String divideStrings(String str1, int str2) {
        char[] strToArray = str1.toCharArray();
        int result1 = strToArray.length / str2;
        char[] result2 = Arrays.copyOfRange(strToArray, 0, result1);
        String result3 = Arrays.toString(result2);
        String result = new String(result3).substring(1, 3 * result2.length - 1).replace(", ", "");
        return result;
    }
}

