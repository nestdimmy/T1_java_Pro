package hm1;

import hm1.annotations.AfterSuite;
import hm1.annotations.AfterTest;
import hm1.annotations.BeforeSuite;
import hm1.annotations.BeforeTest;
import hm1.annotations.Test;
import hm1.context.TestClassContext;
import hm1.context.TestMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestRunner {

    public static void runTests(Class<?> clazz) {
        TestClassContext testClassContext = new TestClassContext();
        for (Method classMethod : clazz.getDeclaredMethods()) {
            if (classMethod.isAnnotationPresent(BeforeSuite.class)) {
                testClassContext.addBeforeSuite(classMethod);
            } else if (classMethod.isAnnotationPresent(BeforeTest.class)) {
                testClassContext.addBeforeTest(classMethod);
            } else if (classMethod.isAnnotationPresent(Test.class)) {
                testClassContext.addTest(classMethod);
            } else if (classMethod.isAnnotationPresent(AfterTest.class)) {
                testClassContext.addAfterTest(classMethod);
            } else if (classMethod.isAnnotationPresent(AfterSuite.class)) {
                testClassContext.addAfterSuite(classMethod);
            }
        }

        testClassContext.getBeforeSuite()
                .ifPresent(method -> invokeMethod(method, clazz));

        Object newClassInstance = getNewClassInstance(clazz);

        for (TestMethod method : testClassContext.getOrderedTests()) {
            for (Method beforeTestMethod : testClassContext.getBeforeTest()) {
                invokeMethod(beforeTestMethod, newClassInstance);
            }

            invokeMethod(method.getMethod(), method.getArguments(), newClassInstance);

            for (Method afterTestMethod : testClassContext.getAfterTest()) {
                invokeMethod(afterTestMethod, newClassInstance);
            }
        }

        testClassContext.getAfterSuite()
                .ifPresent(method -> invokeMethod(method, clazz));
    }

    private static Object getNewClassInstance(Class<?> clazz) {
        try {
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            return declaredConstructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка инициации объекта из класса" + clazz.getSimpleName(), e);
        }

    }

    private static void invokeMethod(Method method, Object clazz) {
        invokeMethod(method, null, clazz);
    }

    private static void invokeMethod(Method method, Object[] args, Object clazz) {
        try {
            if (args != null) {
                method.invoke(clazz, args);
            } else {
                method.invoke(clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
