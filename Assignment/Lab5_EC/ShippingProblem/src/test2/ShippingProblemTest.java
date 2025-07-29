package test2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sqa.main.ShippingVehicle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShippingProblemTest {

    static Stream<org.junit.jupiter.params.provider.Arguments> shippingTestCases() {
        return Stream.of(
            // TC1
            org.junit.jupiter.params.provider.Arguments.of(10, 20, 30, Arrays.asList(10,20,30)),
            // TC2
            org.junit.jupiter.params.provider.Arguments.of(0, 0, 0, Arrays.asList(0, 0, 0)),
            // TC3: เดา logic ว่า (0,0,200) น่าจะตัดเหลือ 100 large, medium, small = 0
            org.junit.jupiter.params.provider.Arguments.of(0, 0, 200, Arrays.asList(100, 0, 0)),
            // TC4
            org.junit.jupiter.params.provider.Arguments.of(600, 0, 0, Arrays.asList(0, 0, 500)),
            // TC5
            org.junit.jupiter.params.provider.Arguments.of(1, 1, 999, Arrays.asList(100, 0, 0)),
            // TC6
            org.junit.jupiter.params.provider.Arguments.of(10, 10, 0, Arrays.asList(0, 10, 10)),
            // TC7 (Negative Small)
            org.junit.jupiter.params.provider.Arguments.of(-5, 2, 3, Arrays.asList(-1)),
            // TC8 (Negative Medium)
            org.junit.jupiter.params.provider.Arguments.of(7, -2, 9, Arrays.asList(-1)),
            // TC9 (Negative Large)
            org.junit.jupiter.params.provider.Arguments.of(8, 4, -1, Arrays.asList(-1)),
            // TC10 (large มากเกิน)
            org.junit.jupiter.params.provider.Arguments.of(0, 0, 150, Arrays.asList(-1)),
            // TC11
            org.junit.jupiter.params.provider.Arguments.of(1, 0, 0, Arrays.asList(0, 0, 1)),
            // TC12
            org.junit.jupiter.params.provider.Arguments.of(0, 1, 0, Arrays.asList(0, 1, 0)),
            // TC13
            org.junit.jupiter.params.provider.Arguments.of(0, 0, 1, Arrays.asList(1, 0, 0))
        );
    }

    @ParameterizedTest(name = "TC{index}: S={0}, M={1}, L={2} => {3}")
    @MethodSource("shippingTestCases")
    @DisplayName("Test ShippingVehicle calculate")
    void testCalculate(int small, int medium, int large, List<Integer> expected) {
        ShippingVehicle sv = new ShippingVehicle();
        List<Integer> result = sv.calculate(small, medium, large);
        assertEquals(expected, result);
    }
}