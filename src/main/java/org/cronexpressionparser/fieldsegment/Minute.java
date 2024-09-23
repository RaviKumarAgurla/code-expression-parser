package org.cronexpressionparser.fieldsegment;

public class Minute extends BaseSegment {
    public Minute(String minutes){
        super(minutes);
        this.maximum = 59;
        this.minimum = 0;
    }
}
