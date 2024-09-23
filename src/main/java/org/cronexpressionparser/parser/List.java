package org.cronexpressionparser.parser;

import org.cronexpressionparser.fieldsegment.BaseSegment;

import java.util.stream.Collectors;

public class List extends BaseParser{

    public List(BaseSegment segment){
        super(segment);
    }
    @Override
    public java.util.List<Integer> generatePossibilities() {
        String[] lists = this.segment.getFieldContent().split(",");

        if (lists.length < 2) {
            throw new RuntimeException(
                    "List does not have valid expression : " + this.segment.getFieldContent());
        }

        return java.util.List.of(lists).stream()
                .flatMap(l -> {
                    try {
                        return BaseParser.get(new BaseSegment(l) {
                            @Override
                            public Integer getMinimum() {
                                return segment.getMinimum();
                            }

                            @Override
                            public Integer getMaximum() {
                                return segment.getMaximum();
                            }

                            @Override
                            public String getFieldContent() {
                                return l;
                            }
                        }).generatePossibilities().stream();
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }).distinct().sorted().collect(Collectors.toList());
    }
}
