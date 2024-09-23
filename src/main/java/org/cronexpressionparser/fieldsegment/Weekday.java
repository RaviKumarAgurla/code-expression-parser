package org.cronexpressionparser.fieldsegment;

public class Weekday extends BaseSegment {
    public Weekday(String weekDay){
        super(weekDay);
        this.maximum = 6;
        this.minimum = 0;
    }
}
