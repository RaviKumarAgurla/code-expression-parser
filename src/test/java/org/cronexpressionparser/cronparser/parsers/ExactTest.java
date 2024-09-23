package org.cronexpressionparser.cronparser.parsers;

import org.cronexpressionparser.fieldsegment.Day;
import org.cronexpressionparser.fieldsegment.Month;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ExactTest {
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testDayExactParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Day("1");
    assertEquals(d.parse(), List
      .of(1));
  }

  @Test
  public void testDayExactParserWithException()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Day("59");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("The value for segment is more than the maximum allowed");
    d.parse();
  }

  @Test
  public void testDayExactParserWithExceptionOfMinimum()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Month("0");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("The value for segment is less than the minimum allowed");
    d.parse();
  }
}
