package christmas;

import christmas.domain.EventController;
import christmas.global.config.Dependency;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        EventController eventController = Dependency.eventController();
        eventController.play();
    }
}
