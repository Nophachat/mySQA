package sqa.main;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sqa.main.TVPlan;
import sqa.main.TVPlan.TVPackage;

import static org.junit.jupiter.api.Assertions.*;
class TVPlanEEDTTest {

    @ParameterizedTest(name="Rule{0}: std={1}, prem={2}, fam={3}, off={4}, live={5}, contract={6}, expect={7}")
    @CsvSource({
        // rule, std, prem, fam, offline, live, contract, expected
        "1, true, false, false, false, false, false, 150",
        "2, true, false, false, true,  false, false, 250",
        "3, true, false, false, false, true,  false, 250",
        "4, true, false, false, true,  true,  false, 350",
        "5, true, false, false, false, false, true,  100",
        "6, true, false, false, true,  false, true,  200",
        "7, true, false, false, false, true,  true,  200",
        "8, true, false, false, true,  true,  true,  300",

        "9, false, true, false, false, false, false, 350",
        "10, false, true, false, true,  false, false, 450",
        "11, false, true, false, false, true,  false, 450",
        "12, false, true, false, true,  true,  false, 550",
        "13, false, true, false, false, false, true,  300",
        "14, false, true, false, true,  false, true,  400",
        "15, false, true, false, false, true,  true,  400",
        "16, false, true, false, true,  true,  true,  500",

        "17, false, false, true,  false, false, false, 450",
        "18, false, false, true,  true,  false, false, 550",
        "19, false, false, true,  false, true,  false, 550",
        "20, false, false, true,  true,  true,  false, 650",
        "21, false, false, true,  false, false, true,  400",
        "22, false, false, true,  true,  false, true,  500",
        "23, false, false, true,  false, true,  true,  500",
        "24, false, false, true,  true,  true,  true,  600",

        // Impossible cases (เลือก package หลายอัน)
        "25, true, true, false, false, false, false, -1",
        "26, true, false, true, false, false, false, -1",
        "27, false, true, true, false, false, false, -1",
        "28, true, true, true, false, false, false, -1"
    })
    void testEEDT(
            int rule,
            boolean std, boolean prem, boolean fam,
            boolean offline, boolean live, boolean contract,
            double expected) {

        int pkgCount = (std ? 1 : 0) + (prem ? 1 : 0) + (fam ? 1 : 0);
        // กรณี Impossible (เลือกมากกว่า 1 package)
        if (pkgCount != 1) {
            assertEquals(-1, expected, "Rule-"+rule+" Impossible: เลือกหลายแพ็กเกจไม่ได้");
            return;
        }
        // เลือกแพ็กเกจเดียวเท่านั้น
        TVPackage pkg = std ? TVPackage.STANDARD : prem ? TVPackage.PREMIUM : TVPackage.FAMILY;
        TVPlan plan = new TVPlan(offline, live, contract);
        double result = plan.pricePerMonth(pkg);
        assertEquals(expected, result, 0.01, "Rule-"+rule+" ค่าบริการรวมต่อเดือนผิด");
    }
}