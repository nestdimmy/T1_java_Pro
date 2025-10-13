package hm1.context;

import hm1.annotations.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TestClassContext {
    private final List<Method> beforeTest = new ArrayList<>();
    private final List<Method> afterTest = new ArrayList<>();
    private final List<TestMethod> tests = new ArrayList<>();

    private Method beforeSuite;
    private Method afterSuite;

    public void addTest(Method method) {
        Test annotation = method.getAnnotation(Test.class);
        if (annotation.priority() < 1 || annotation.priority() > 10) {
            throw new IllegalArgumentException("Значение priority у @Test методов может быть от 1 до 10");
        }

        this.tests.add(new TestMethod(method));
    }

    public void addBeforeSuite(Method method) {
        if (beforeSuite != null) {
            throw new IllegalArgumentException("Только один метод с аннотацией @BeforeSuite" +
                    " может быть представлен в рамках одного тест класа");
        }

        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException(
                    "Аннотация @BeforeSuite может применяться только на статические методы"
            );
        }

        this.beforeSuite = method;
    }

    public void addAfterSuite(Method method) {
        if (afterSuite != null) {
            throw new IllegalArgumentException("Только один метод с аннотацией @AfterSuite" +
                    " может быть представлен в рамках одного тест класа");
        }

        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException(
                    "Аннотация @AfterSuite может применяться только на статические методы"
            );
        }

        this.afterSuite = method;
    }

    public void addBeforeTest(Method method) {
        this.beforeTest.add(method);
    }

    public void addAfterTest(Method method) {
        this.afterTest.add(method);
    }

    public Optional<Method> getBeforeSuite() {
        return Optional.ofNullable(beforeSuite);
    }

    public Optional<Method> getAfterSuite() {
        return Optional.ofNullable(afterSuite);
    }

    public List<Method> getBeforeTest() {
        return beforeTest;
    }

    public List<Method> getAfterTest() {
        return afterTest;
    }

    public List<TestMethod> getOrderedTests() {
        return tests.stream()
                .sorted(Comparator.comparingInt(TestMethod::getPriority))
                .toList();
    }
}
