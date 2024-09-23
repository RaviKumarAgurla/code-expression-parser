package org.cronexpressionparser.fieldsegment;

public class Month extends BaseSegment {
    public Month(String month){
        super(month);
        this.maximum = 12;
        this.minimum = 1;
    }
}
