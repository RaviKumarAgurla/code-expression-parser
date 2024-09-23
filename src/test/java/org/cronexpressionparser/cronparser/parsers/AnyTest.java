package org.cronexpressionparser.cronparser.parsers;

import org.cronexpressionparser.fieldsegment.Day;
import org.cronexpressionparser.fieldsegment.Weekday;
import org.cronexpressionparser.fieldsegment.BaseSegment;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import static org.junit.Assert.*;

public class AnyTest {

  @Test
  public void testDayAnyParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    BaseSegment d = new Day("*");

    assertEquals(d.parse(), List
      .of( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
        25, 26, 27, 28, 29, 30, 31));
  }

  @Test
  public void testWeekdayAnyParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Weekday("0-6");
    assertEquals(d.parse(), List
      .of(0, 1, 2, 3, 4, 5, 6));
  }
}
