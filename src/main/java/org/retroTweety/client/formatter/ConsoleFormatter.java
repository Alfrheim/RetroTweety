package org.retroTweety.client.formatter;

import org.retroTweety.controller.out.Message;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by alfrheim on 24/02/17.
 */
public class ConsoleFormatter {

    public String format(Message message) {
        String formattedTime = this.getProperTimeFormat(message.getCreated());
        return String.format("%s - %s %s", message.getUser().getUserfName(), message.getMessage(), formattedTime);
    }

    private Duration elapsedTime(ZonedDateTime time) {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        return Duration.between(time, currentDate);
    }

    private String getProperTimeFormat(ZonedDateTime zonedDateTime) {
        Duration duration = this.elapsedTime(zonedDateTime);
        long elapsedTime;
        String time;
        if (duration.toHours() > 0) {
            elapsedTime = duration.toHours();
            time = "hour";
        } else if (duration.toMinutes() > 0) {
            elapsedTime = duration.toMinutes();
            time = "minute";
        } else  {
            elapsedTime = duration.getSeconds();
            time = "second";
        }

        if(isPlural(elapsedTime)) {
            time += "s";
        }

        return String.format("(%d %s ago)", elapsedTime, time);
    }

    private boolean isPlural(long elapsedTime) {
        return elapsedTime > 1;
    }
}
