package org.cronexpressionparser;

import org.cronexpressionparser.fieldsegment.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class CronExpressionParser {
    HashMap<String, List> segmentMap;
    String command;
    String cronExpression;

    static List<String> displayOrder = List.of("minute", "hour", "dayOfMonth", "month", "dayOfWeek");
    static Map<String, String> displayString = Map
            .of("dayOfMonth", "day of month", "dayOfWeek", "day of week");

    public CronExpressionParser(String cronExpression) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.cronExpression = cronExpression;
        this.segmentMap = new HashMap<>();
        parseExpression();
    }
    public String getCronExpression() {
        return cronExpression;
    }
    public void parseExpression() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[] parts = cronExpression.split("\\s+");
        segmentMap.put("minute", new Minute(parts[0]).parse());
        segmentMap.put("hour", new Hour(parts[1]).parse());
        segmentMap.put("dayOfMonth", new Day(parts[2]).parse());
        segmentMap.put("month", new Month(parts[3]).parse());
        segmentMap.put("dayOfWeek", new Weekday(parts[4]).parse());
        command = parts[5];
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String section : displayOrder) {
            if (this.segmentMap.get(section) == null) {
                continue;
            }
            String displayString = this.displayString.getOrDefault(section, section);
            sb.append(String.format(displayString + " ".repeat(14 - displayString.length()) + "%s",
                    this.segmentMap.get(section).stream().map(i -> i.toString()).collect(
                            Collectors.joining(" "))));
            sb.append(System.getProperty("line.separator"));
        }
        sb.append(String.format("command       %s", this.command));
        return sb.toString();
    }

}
