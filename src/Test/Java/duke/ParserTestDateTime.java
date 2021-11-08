package duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import duke.Parser;

import static org.junit.jupiter.api.Assertions.*;

class ParserTestDateTime {

    @Test
    void parseDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date3 = LocalDateTime.parse("2021-11-14 23:59",formatter);
        String a = "2021-11-14 23:59";
        assertEquals(date3, Parser.parseDateTime(a));
    }
}