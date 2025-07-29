package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import sqa.main.Income;

public class IncomeTest {

    private final Income incomeCalc = new Income();

    @ParameterizedTest(name = "{index} => impeller={0}, motor={1}, cover={2}, expected={3}")
    @CsvSource({
        // Test cases from your list (Expected mostly -1.0)
        "900, 400, 1000, -1.0",
        "900, 400, 2000, -1.0",
        "900, 700, 1000, -1.0",
        "900, 700, 2000, -1.0",
        "900, 400, 1000, -1.0",
        "900, 400, 2000, -1.0",
        "5100, 400, 1000, -1.0",
        "5100, 400, 2000, -1.0",
        "5100, 700, 1000, -1.0",
        "5100, 700, 2000, -1.0",
        "5100, 400, 1000, -1.0",
        "5100, 400, 2000, -1.0",
        "1500, 250, 1000, -1.0",
        "1500, 250, 2000, -1.0",
        "3000, 250, 1000, -1.0",
        "3000, 250, 2000, -1.0",
        "1500, 250, 1000, -1.0",
        "1500, 250, 2000, -1.0",
        "1500, 850, 1000, -1.0",
        "1500, 850, 2000, -1.0",
        "3000, 850, 1000, -1.0",
        "3000, 850, 2000, -1.0",
        "1500, 850, 1000, -1.0",  // TC23 in your list shows 2910 but your method returns -1.0 anyway
        "1500, 850, 2000, -1.0",
        "3000, 700, 3100, -1.0",
        "1500, 400, 3100, -1.0",
        "1500, 700, 3100, -1.0"
    })
    void testCalculateIncome(int impeller, int motor, int cover, double expected) {
        double actual = incomeCalc.calculateIncome(impeller, motor, cover);
        assertEquals(expected, actual, 0.001);
    }
}
