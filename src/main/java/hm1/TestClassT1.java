package hm1;

import hm1.annotations.AfterSuite;
import hm1.annotations.AfterTest;
import hm1.annotations.BeforeSuite;
import hm1.annotations.BeforeTest;
import hm1.annotations.CsvSource;
import hm1.annotations.Test;

public class TestClassT1 {

    @BeforeSuite
    public static void beforeSuiteMethod() {
        System.out.println("Я beforeSuiteMethod");
    }

    @AfterSuite
    public static void afterSuiteMethod() {
        System.out.println("Я afterSuiteMethod");
    }


    @BeforeTest
    public void beforeTestActionOne() {
        System.out.println("Я beforeTestActionOne");
    }

    @BeforeTest
    public void beforeTestActionTwo() {
        System.out.println("Я beforeTestActionTwo");
    }

    @Test(priority = 2)
    public void test1() {
        System.out.println("Я test1");
    }

    @Test(
            priority = 12313,
            arguments = @CsvSource("argument1,12345")
    )
    public void test2(String one, Integer two) {
        System.out.println("Я test2, arg1: " + one + " arg2: " + two);

    }

    @AfterTest
    public void afterTestActionOne() {
        System.out.println("Я afterTestActionOne");
    }

    @AfterTest
    public void afterTestActionTwo() {
        System.out.println("Я afterTestActionTwo");
    }

}
