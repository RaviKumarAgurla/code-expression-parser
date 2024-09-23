package org.cronexpressionparser.fieldsegment;

public class Day extends BaseSegment {
    public Day(String day){
        super(day);
        this.maximum = 31;
        this.minimum = 1;
    }
}
