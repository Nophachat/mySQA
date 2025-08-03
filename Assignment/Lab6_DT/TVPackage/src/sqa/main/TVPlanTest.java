package sqa.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sqa.main.TVPlan;
import sqa.main.TVPlan.TVPackage;

public class TVPlanTest {

    /**
     * ในแต่ละ row:
     * rule,isStandard, isPremium, isFamily, offline, contract, expected
     * Impossible case expected = -1
     */
    @ParameterizedTest(name="Rule({0}): S={1} P={2} F={3} offline={4} contract={5} ==> expect={6}")
    @CsvSource({
        // Rule, S,   P,   F,    offline, contract, expected
        "1,  true, true, true,  true,  true,  -1",
        "2,  true, true, true,  true,  false, -1",
        "3,  true, true, true,  false, true,  -1",
        "4,  true, true, true,  false, false, -1",
        "5,  true, true, false, true,  true,  -1",
        "6,  true, true, false, true,  false, -1",
        "7,  true, true, false, false, true,  -1",
        "8,  true, true, false, false, false, -1",
        "9,  true, false, true, true,  true,  -1",
        "10, true, false, true, true,  false, -1",
        "11, true, false, true, false, true,  -1",
        "12, true, false, true, false, false, -1",
        "13, true, false, false, true,  true,  200",
        "14, true, false, false, true,  false, 250",
        "15, true, false, false, false, true,  100",
        "16, true, false, false, false, false, 150",
        "17, false, true, true,  true,  true,  -1",
        "18, false, true, true,  true,  false, -1",
        "19, false, true, true,  false, true,  -1",
        "20, false, true, true,  false, false, -1",
        "21, false, true, false, true,  true,  400",
        "22, false, true, false, true,  false, 450",
        "23, false, true, false, false, true,  300",
        "24, false, true, false, false, false, 350",
        "25, false, false, true, true,  true,  500",
        "26, false, false, true, true,  false, 550",
        "27, false, false, true, false, true,  400",
        "28, false, false, true, false, false, 450",
        "29, true, true, true,  true,  true,  -1",
        "30, true, true, true,  true,  false, -1",
        "31, true, true, true,  false, true,  -1",
        "32, true, true, true,  false, false, -1"
    })
    void testTVPlanRule(
            int rule, boolean isStandard, boolean isPremium, boolean isFamily,
            boolean offline, boolean contract, double expected) {

        // Impossible case: ต้องมีเลือก package เดียวเท่านั้น
        int selected = (isStandard ? 1 : 0) + (isPremium ? 1 : 0) + (isFamily ? 1 : 0);
        if (selected != 1) {
            assertEquals(-1, expected, "Rule " + rule + ": Impossible, multiple packages selected");
            return;
        }

        TVPlan.TVPackage pkg = isStandard ? TVPlan.TVPackage.STANDARD
                            : isPremium ? TVPlan.TVPackage.PREMIUM
                            : TVPlan.TVPackage.FAMILY;
        TVPlan plan = new TVPlan(offline, false, contract);

        double result = plan.pricePerMonth(pkg);
        assertEquals(expected, result, 0.001, "Rule " + rule);
    }
}