package org.cronexpressionparser.cronparser;

import org.cronexpressionparser.CronExpressionParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;

public class CronParserTest {
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testSimpleCronExpression()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    assertEquals(new CronExpressionParser("*/15 0 1,5 * 1-5 /usr/bin/find").toString(), "minute        0 15 30 45\n"
      + "hour          0\n"
      + "day of month  1 5\n"
      + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
      + "day of week   1 2 3 4 5\n"
      + "command       /usr/bin/find");
  }

  @Test
  public void testCronExpressionWithComplexLists()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    assertEquals(new CronExpressionParser("*/15 0 1-5,1-15 * 1-5 /usr/bin/find").toString(), "minute        0 15 30 45\n"
      + "hour          0\n"
      + "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n"
      + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
      + "day of week   1 2 3 4 5\n"
      + "command       /usr/bin/find");
  }

  @Test
  public void testCronExpressionWithWrongStep()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step size is more than maximum value");
    new CronExpressionParser("*/105 0 1,5 * 1-5 /usr/bin/find").toString();
  }

  @Test
  public void testCronExpressionWithWrongArgument()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Invalid segment expression :");
    new CronExpressionParser("").toString();
  }
}
