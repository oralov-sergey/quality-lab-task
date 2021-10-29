package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CommonActions extends TestBase {

    public String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("LLLL yyyy")).toLowerCase(Locale.ROOT);
    }
}