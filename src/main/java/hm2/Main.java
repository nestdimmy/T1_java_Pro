package hm2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    private static final List<Employee> employeeData = List.of(
            new Employee("Тест", 41, "Инженер"),
            new Employee("Тест1", 28, "не Инженер"),
            new Employee("Тест2", 31, "да Инженер"),
            new Employee("Тест3", 67, "Инженер"),
            new Employee("Тест4", 98, "Инженер"),
            new Employee("Тест5", 23, "Инженер")
    );

    private static Predicate<Employee> isEngineer = emp -> "Инженер".equals(emp.position());
    private static List<String> wordsData = List.of("1", "12", "123", "1234", "12345");
    private static List<String> wordsTaskSevenData = List.of("a", "b", "cw", "ca", "cd", "d", "e");
    private static List<String> wordsTaskEightData = List.of("123 321 33", "acas ascasc csacascasc", "ccccc cc", "a", "2123faw awcawcawcawc");
    private static String mapsData = "hello test testss test ssss testsss sss tests test hello";

    public static void main(String[] args) {
        runTaskOne();
        runTaskTwo();
        runTaskThree(employeeData);
        runTaskFour(employeeData);
        runTaskFive(wordsData);
        runTaskSix(mapsData);
        runTaskSeven(wordsTaskSevenData);
        runTaskEight(wordsTaskEightData);
    }

    private static void runTaskOne() {
        ArrayList<Integer> testData = new ArrayList<>(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13));
        System.out.println("Найдите в списке целых чисел 3-е наибольшее число");
        Integer thirdValue = testData.stream()
                .sorted(Integer::compareTo)
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неверные входные данные"));
        System.out.println("Результат:" + thirdValue);
    }

    private static void runTaskTwo() {
        ArrayList<Integer> testData = new ArrayList<>(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13));
        System.out.println("Найдите в списке целых чисел 3-е наибольшее «уникальное» число");
        Integer thirdValue = testData.stream()
                .distinct()
                .sorted(Integer::compareTo)
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неверные входные данные"));
        System.out.println("Результат:" + thirdValue);
    }

    private static void runTaskThree(List<Employee> employees) {
        System.out.println("Имеется список объектов типа Сотрудник (имя, возраст, должность), " +
                "необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер»," +
                " в порядке убывания возраста");
        String result = employees.stream()
                .sorted(Comparator.comparing(Employee::age).reversed())
                .filter(isEngineer)
                .limit(3)
                .map(Employee::name)
                .collect(Collectors.joining(","));

        System.out.println(result);
    }

    private static void runTaskFour(List<Employee> employeeData) {
        System.out.println("Имеется список объектов типа Сотрудник (имя, возраст, должность), " +
                "посчитайте средний возраст сотрудников с должностью «Инженер»");

        OptionalDouble averageAge = employeeData.stream()
                .filter(isEngineer)
                .map(Employee::age)
                .mapToInt(Integer::intValue)
                .average();

        if (averageAge.isEmpty()) {
            System.out.println("Не удалось посчитать возраст");
            return;
        }

        System.out.println("Средний возраст - " + averageAge.getAsDouble());

    }

    private static void runTaskFive(List<String> wordsData) {
        System.out.println("Найдите в списке слов самое длинное");
        String longestWord = wordsData.stream()
                .max(Comparator.comparing(String::length))
                .orElse(null);

        System.out.println("Результат: " + longestWord);
    }

    private static void runTaskSix(String mapsData) {
        System.out.println("Имеется строка с набором слов в нижнем регистре, разделенных пробелом." +
                " Постройте хеш-мапы, в которой будут хранится пары: " +
                "слово - сколько раз оно встречается во входной строке");

        Map<String, Integer> solutionOne = Arrays.stream(mapsData.split("\\s"))
                .collect(Collectors.toMap(
                        word -> word,
                        word -> 1, (current, newVal) -> current + 1
                ));

        Map<String, Long> solutionTwo = Arrays.stream(mapsData.split("\\s"))
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        System.out.println("Решение 1: " + solutionOne);
        System.out.println("Решение 2: "+ solutionTwo);
    }


    private static void runTaskSeven(List<String> data) {
        System.out.println("Отпечатайте в консоль строки из списка в порядке увеличения длины слова," +
                " если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок");

        data.stream()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .forEach(System.out::println);
    }

    private static void runTaskEight(List<String> data) {
        System.out.println("Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом, " +
                "найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них");

        data.stream()
                .flatMap(str -> Arrays.stream(str.split("\\s")))
                .max(Comparator.comparing(String::length))
                .ifPresent(System.out::println);
    }

}
