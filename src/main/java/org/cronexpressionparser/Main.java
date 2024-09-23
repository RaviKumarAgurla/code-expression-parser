package org.cronexpressionparser;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CronExpressionParser cep = new CronExpressionParser(args[0]);
        System.out.println(cep);
    }
}