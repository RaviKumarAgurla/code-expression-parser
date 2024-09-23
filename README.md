# Cron Expression Parser

This command-line application parses a cron expression and expands each field to display the times at which the command will run.

It follows the standard cron format, consisting of five time fields: minute, hour, day of the month, month, and day of the week, followed by the command to be executed. The cron expression should be provided as a single argument to the application.

```~$ your-program "*/15 0 1,15 * 1-5 /usr/bin/find"```



## Limitations
- This does not handle the special time strings such as "@yearly"
- This does not handle if the month has less than 31 days or not
- Test coverage is not complete

## Instructions to run the program
```
java -jar cron-expression-parser.jar "*/3 0,7 1,6 3-5 3-4/4 /usr/bin/find"
```

The output is formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

For example, the following input argument:

```java -jar cron-expression-parser.jar "*/3 0,7 1,6 3-5 3-4/4 /usr/bin/find"```

Yields the following output:

```
minute        0 3 6 9 12 15 18 21 24 27 30 33 36 39 42 45 48 51 54 57
hour          0 7
day of month  1 6
month         3 4 5
day of week   3
command       /usr/bin/find

```

## Tests

The tests are not exhaustive but are present in the `test/` directory. They do cover basic cases of Parsers and over tests of an expression to the required output.