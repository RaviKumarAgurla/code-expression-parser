package org.cronexpressionparser.cronparser.parsers;

import org.cronexpressionparser.fieldsegment.Minute;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StepTest {
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testStepParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("*/15");
    assertEquals(d.parse(), List.of(0, 15,30,45));
  }

  @Test
  public void testStepWithMissingValues()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("*/");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step does not have valid expression : */");
    d.parse();
  }

  @Test
  public void testStepWithInvalidStepStart()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("a/15");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step does not have valid expression : a/15");
    d.parse();
  }

  @Test
  public void testStepWithWrongStepStart()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("60/15");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step start is more than maximum value");
    d.parse();
  }

  @Test
  public void testStepWithWrongStepValue()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    org.cronexpressionparser.fieldsegment.BaseSegment d = new Minute("*/60");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step size is more than maximum value");
    d.parse();
  }

}
