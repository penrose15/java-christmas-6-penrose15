package christmas.domain.calendar;

import java.util.List;

public class StarDate {
    private static final List<Integer> STAR_DATE
            = List.of(3,10,17,24,25,31);


    public static boolean checkStarDate(int date) {
        return STAR_DATE.contains(date);
    }

}
