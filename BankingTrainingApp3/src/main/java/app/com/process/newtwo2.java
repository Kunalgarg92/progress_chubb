package app.com.process;

import java.time.*;
import java.util.*;
import java.util.stream.*;

public class newtwo2 {
    public static void main(String[] args) {
        int year = 2025;
        Month month = Month.NOVEMBER;
        List<LocalDate> weekends = getsatsun(year, month);
        weekends.forEach(System.out::println);
    }
    public static List<LocalDate> getsatsun(int year, Month month) {
        LocalDate start = LocalDate.of(year, month, 1);
        int lengthOfMonth = start.lengthOfMonth(); 
        return IntStream.rangeClosed(1, lengthOfMonth)
                .mapToObj(day -> LocalDate.of(year, month, day))
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                                date.getDayOfWeek() == DayOfWeek.SUNDAY)
                .collect(Collectors.toList());
    }
}
