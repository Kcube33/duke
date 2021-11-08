package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date1 = LocalDateTime.parse("2021-11-14 23:59",formatter);
        Deadline t = new Deadline("To do testing", date1);
        assertEquals("SUNDAY 2021-11-14 11:59 PM", t.getBy());
    }
}