package org.cronexpressionparser.parser;

import org.cronexpressionparser.fieldsegment.BaseSegment;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class BaseParser {
    BaseSegment segment;

    protected static final String ANY = "*";
    protected static final String LIST = ",";
    protected static final String STEP = "/";
    protected static final String RANGE = "-";
    protected static final String Fixed = "e";

    private static final Map<String, Class> expressionParserMap = Map.of(
            ANY, Any.class,
            LIST, List.class,
            STEP, Step.class,
            RANGE, Range.class,
            Fixed, Fixed.class
    );
    public BaseParser(BaseSegment segment){
        this.segment = segment;
    }

    public static BaseParser get(BaseSegment segment) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String parser = null;
        if (segment.getFieldContent().equals(ANY)) parser = ANY;
        if (segment.getFieldContent().matches(".*,.*")) parser = LIST;
        if (segment.getFieldContent().matches("[0-9]+-[0-9]+")) parser = RANGE;
        if (segment.getFieldContent().matches(".*\\/.*")) parser = STEP;
        if (segment.getFieldContent().matches("^[0-9]+$")) parser = Fixed;

        if (parser == null) throw new RuntimeException("Invalid segment expression : " + segment.getFieldContent());

        return (BaseParser) expressionParserMap
                .get(parser)
                .getDeclaredConstructor(BaseSegment.class).newInstance(segment);
    }

    public abstract java.util.List<Integer> generatePossibilities();
}
