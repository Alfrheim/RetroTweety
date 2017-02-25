package org.retroTweety.client.formatter;

import org.junit.Before;
import org.junit.Test;
import org.retroTweety.controller.out.Message;
import org.retroTweety.controller.out.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

/**
 * Created by alfrheim on 25/02/17.
 */
public class ConsoleFormatterTest {

    private ConsoleFormatter consoleFormatter;

    @Before
    public void setUp() throws Exception {
      consoleFormatter = new ConsoleFormatter();

    }

    @Test
    public void format_givenSecondMessage_returnsSecond() throws Exception {
        User pere = new User("pere");
        Message message = new Message(pere, "is sunny", ZonedDateTime.now(ZoneId.of("UTC")));

        String result = consoleFormatter.format(message);

        assertTrue(result.contains("second"));
        assertTrue(!result.contains("seconds"));
    }

    @Test
    public void format_givenSecondsMessage_returnsSeconds() throws Exception {
        User pere = new User("pere");
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("UTC")).minus(2, ChronoUnit.SECONDS);
        Message message = new Message(pere, "is sunny", time);

        String result = consoleFormatter.format(message);

        assertTrue(result.contains("seconds"));
    }

    @Test
    public void format_givenMinutsMessage_returnsMinutes() throws Exception {
        User pere = new User("pere");
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("UTC")).minus(2, ChronoUnit.MINUTES);
        Message message = new Message(pere, "is sunny", time);

        String result = consoleFormatter.format(message);

        assertTrue(result.contains("minutes"));
    }

    @Test
    public void format_given2HoursMessage_returns2Hours() throws Exception {
        User pere = new User("pere");
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("UTC")).minus(2, ChronoUnit.HOURS);
        Message message = new Message(pere, "is sunny", time);

        String result = consoleFormatter.format(message);

        assertTrue(result.contains("2 hours"));
    }

}