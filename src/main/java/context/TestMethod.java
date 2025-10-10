package context;

import annotations.CsvSource;
import annotations.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;

public class TestMethod {

    private final int priority;
    private final Method method;
    private final Object[] arguments;

    public TestMethod(Method method) {
        this.method = method;

        Parameter[] parameters = method.getParameters();
        Test testData = method.getAnnotation(Test.class);
        this.priority = testData.priority();
        if (parameters.length == 0) {
            this.arguments = null;
        } else {
            if (testData.arguments() != null) {
                this.arguments = getTestArguments(parameters, testData.arguments());
            } else {
                throw new IllegalArgumentException("@Test метод имеет аргументы но параметр arguments не заполнен");
            }
        }
    }

    private Object[] getTestArguments(Parameter[] parameters, CsvSource args) {
        if (parameters.length == 0) {
            throw new IllegalArgumentException("Аннотация @CsvSource" +
                    " может применяться только на методы имеющие аргументы в сигнатуре");
        }

        String[] passedArguments = getPassedArguments(parameters, args);
        LinkedList<Object> castedArguments = new LinkedList<>();
        for (int i = 0; i < parameters.length; i++) {
            String passedArg = passedArguments[i].trim();
            Class<?> methodArgType = parameters[i].getType();
            try {
                castedArguments.add(convertStringToType(passedArg, methodArgType));
            } catch (Exception e) {
                throw new IllegalArgumentException("Ошибка конвертации аргумента со значением " + passedArg
                        + " к типу данных " + methodArgType.getSimpleName());
            }
        }
        return castedArguments.toArray();

    }

    private String[] getPassedArguments(Parameter[] parameters, CsvSource args) {
        String arguments = args.value();
        if (arguments == null || arguments.isEmpty()) {
            throw new IllegalArgumentException("Аргументы в @CsvSource не переданы");
        }

        String[] argsFromString = arguments.split(",");
        if (argsFromString.length != parameters.length) {
            throw new IllegalArgumentException("Кол-во аргументов в аннотации @CsvSource " +
                    "не соотвествует кол-во аргументов метода"
            );
        }
        return argsFromString;
    }

    private Object convertStringToType(String value, Class<?> targetType) {
        if (value == null) {
            return null;
        }

        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.valueOf(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.valueOf(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.valueOf(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.valueOf(value);
        }

        //можно добавить еще типы которые нужны
        throw new UnsupportedOperationException("Преобразование типа " + targetType + " не реализовано");
    }

    public int getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }
}
