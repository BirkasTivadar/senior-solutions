package emapp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeDataDrivenTest {

    private int[][] values = {{1970, 0}, {1980, 10}, {2000, 30}};

    @RepeatedTest(value = 3, name = "Get age {currentRepetition} / {totalRepetitions}")
    void testGetAge(RepetitionInfo repetitionInfo) {

        System.out.println("REP:" + repetitionInfo.getCurrentRepetition());

        Employee employee = new Employee("John Doe", 1970);

        assertEquals(values[repetitionInfo.getCurrentRepetition() - 1][1],
                employee.getAge(values[repetitionInfo.getCurrentRepetition() - 1][0]));
    }
}
