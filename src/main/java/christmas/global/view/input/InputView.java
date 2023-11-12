package christmas.global.view.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String input() {
        return Console.readLine();
    }

    public static int inputNumber() {
        String input = Console.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("[ERROR] 숫자를 입력해주세요");
        }
    }
}
