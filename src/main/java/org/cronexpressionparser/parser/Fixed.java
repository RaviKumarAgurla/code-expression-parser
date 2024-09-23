package org.cronexpressionparser.parser;

import org.cronexpressionparser.fieldsegment.BaseSegment;

import java.util.List;

public class Fixed extends BaseParser{
    public Fixed(BaseSegment segment){
        super(segment);
    }

    @Override
    public List<Integer> generatePossibilities() {
        Integer value = Integer.valueOf(this.segment.getFieldContent());
        if (value > this.segment.getMaximum()) {
            throw new RuntimeException("The value for segment is more than the maximum allowed");
        }

        if (value < this.segment.getMinimum()) {
            throw new RuntimeException("The value for segment is less than the minimum allowed");
        }

        return List.of(value);
    }
}
