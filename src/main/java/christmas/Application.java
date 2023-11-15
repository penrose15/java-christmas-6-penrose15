package christmas;

import christmas.domain.EventController;
import christmas.global.config.Dependency;
import christmas.global.view.output.OutputView;

import static christmas.global.exception.CommonExceptionMessage.EXCEPTION_PREFIX;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try {
            EventController eventController = Dependency.eventController();
            eventController.play();
        } catch (Exception e) {
            OutputView.println(EXCEPTION_PREFIX.get() + e.getMessage());
            throw e;
        }
    }
}
