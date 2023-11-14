package christmas.global.view.io;

import christmas.domain.calendar.EventCalendar;
import christmas.domain.calendar.dto.DateDto;
import christmas.global.view.input.InputView;
import christmas.global.view.output.OutputView;

import static christmas.global.view.message.OrderStaticMessage.EXPECTED_VISIT_DATE;
import static christmas.global.view.message.OrderStaticMessage.START_MESSAGE;

public class EventCalendarView implements InteractionRepeatable{
    private final EventCalendar eventCalendar;

    public EventCalendarView(EventCalendar eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    public int inputVisitDate() {
        OutputView.println(START_MESSAGE.get());
        OutputView.println(EXPECTED_VISIT_DATE.get());

        return supplyInteraction(() -> {
            String input = InputView.input();
            DateDto dateDto = new DateDto(input);

            return eventCalendar.takeReservation(dateDto.date());
        });
    }
}
