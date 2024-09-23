package org.cronexpressionparser.fieldsegment;

import org.cronexpressionparser.parser.BaseParser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseSegment {
    String fieldContent;
    Integer minimum;
    Integer maximum;
    List<Integer> values = new ArrayList<>();
    public BaseSegment(String content){
        this.fieldContent = content;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public String getFieldContent() {
        return fieldContent;
    }

    public List<Integer> parse() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        values = (List<Integer>) BaseParser.get(this).generatePossibilities();
        return values;
    }

    public String toString() {
        return values.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

}
