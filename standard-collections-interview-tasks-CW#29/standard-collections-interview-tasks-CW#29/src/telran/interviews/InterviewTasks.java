package telran.interviews;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InterviewTasks {
    static public void displayShuffled(int[] ar) {
        IntStream.of(ar)
                 .boxed()
                 .collect(Collectors.toList())
                 .forEach(e -> System.out.print(e + " "));
    }

    static public List<DateRole> rolesInDates(List<DateRole> datesRoles, List<LocalDate> dates) {
        return dates.stream()
                    .map(date -> {
                        String role = datesRoles.stream()
                                                .filter(dr -> !dr.date().isAfter(date))
                                                .max(Comparator.comparing(DateRole::date))
                                                .map(DateRole::role)
                                                .orElse(null);
                        return new DateRole(date, role);
                    })
                    .collect(Collectors.toList());
    }
}

record DateRole(LocalDate date, String role) {}