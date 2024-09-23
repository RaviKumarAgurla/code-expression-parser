package org.cronexpressionparser.fieldsegment;

public class Hour extends BaseSegment {
    public Hour(String hour){
        super(hour);
        this.maximum = 59;
        this.minimum = 0;
    }
}
