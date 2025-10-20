package calculator;

import java.util.regex.Pattern;

public class Calculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // 문자열 안에 "\n"이 들어오면 실제 줄바꿈으로 변환
        input = input.replace("\\n", "\n");

        String[] numbers;
        if (input.startsWith("//")) {
            numbers = splitWithCustomDelimiter(input);
        } else {
            numbers = input.split("[,:]");
        }

        return sum(numbers);
    }

    private String[] splitWithCustomDelimiter(String input) {
        String[] parts = input.split("\n", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다. 예: //;\\n1;2;3");
        }

        String customDelimiter = parts[0].substring(2);
        String numbersPart = parts[1];

        return numbersPart.split(Pattern.quote(customDelimiter));
    }

    private int sum(String[] numbers) {
        int result = 0;

        for (String number : numbers) {
            if (number.isEmpty()) continue;
            try {
                int value = Integer.parseInt(number);
                if (value < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다: " + value);
                }
                result += value;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자 이외의 값이 포함되어 있습니다: " + number);
            }
        }

        return result;
    }
}
