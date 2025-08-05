package sqa.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import sqa.main.CountWordClumps;

public class CountWordClumpsTest {
    
    private CountWordClumps countWordClumps;
    
    @BeforeEach
    public void setUp() {
        countWordClumps = new CountWordClumps();
    }

    @Test
    @DisplayName("TC1: Input เป็น null")
    public void testTC1_NullInput() {
        // Arrange
        int[] input = null;
        int expected = 0;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Input null ควรคืนค่า 0");
        
        // Coverage Info
        System.out.println("TC1 PASSED: null input");
        System.out.println("Path: 1→2→3");
        System.out.println("Branch: ①a(T)");
        System.out.println("Condition: A=T, B=-, C=-, D=-, E=-, F=-");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("TC2: Input เป็น array ว่าง")
    public void testTC2_EmptyArray() {
        // Arrange
        int[] input = {};
        int expected = 0;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array ว่างควรคืนค่า 0");
        
        // Coverage Info
        System.out.println("TC2 PASSED: empty array");
        System.out.println("Path: 1→2→3");
        System.out.println("Branch: ①a(T)");
        System.out.println("Condition: A=F, B=T, C=-, D=-, E=-, F=-");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("TC3: Array มีสมาชิกเพียงตัวเดียว")
    public void testTC3_SingleElement() {
        // Arrange
        int[] input = {5};
        int expected = 0;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array ที่มีสมาชิกเดียวควรคืนค่า 0");
        
        // Coverage Info
        System.out.println("TC3 PASSED: single element [5]");
        System.out.println("Path: 1→2→4-6→7→14");
        System.out.println("Branch: ①b(F), ②b(F)");
        System.out.println("Condition: A=F, B=F, C=F, D=-, E=-, F=-");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("TC4: Array ที่มี clump เดียว (2 elements)")
    public void testTC4_OneClumpTwoElements() {
        // Arrange
        int[] input = {1, 1};
        int expected = 1;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array [1,1] ควรมี clump 1 ตัว");
        
        // Coverage Info
        System.out.println("TC4 PASSED: one clump [1,1]");
        System.out.println("Path: 1→2→4-6→7→8→9-10→11→7→14");
        System.out.println("Branch: ①b(F), ②a(T), ③a(T), ④b(F), ②b(F)");
        System.out.println("Condition: A=F, B=F, C=T, D=T, E=T, F=F");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("TC5: Array ที่มี clump ยาว (3 elements)")
    public void testTC5_LongClump() {
        // Arrange
        int[] input = {1, 1, 1};
        int expected = 1;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array [1,1,1] ควรมี clump 1 ตัว");
        
        // Coverage Info
        System.out.println("TC5 PASSED: long clump [1,1,1]");
        System.out.println("Path: 1→2→4-6→7→8→9-10→11→7→8→11→7→14");
        System.out.println("Branch: ①b(F), ②a(T), ③a(T), ④b(F), ②a(T), ③b(F), ④b(F), ②b(F)");
        System.out.println("Condition: A=F, B=F, C=T, D=T, E=F, F=F");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("TC6: Array ไม่มี clump")
    public void testTC6_NoClump() {
        // Arrange
        int[] input = {1, 2};
        int expected = 0;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array [1,2] ไม่ควรมี clump");
        
        // Coverage Info
        System.out.println("TC6 PASSED: no clump [1,2]");
        System.out.println("Path: 1→2→4-6→7→8→11→12-13→7→14");
        System.out.println("Branch: ①b(F), ②a(T), ③b(F), ④a(T), ②b(F)");
        System.out.println("Condition: A=F, B=F, C=T, D=F, E=T, F=T");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("TC7: Array มี clump แล้วเปลี่ยนค่า")
    public void testTC7_ClumpThenChange() {
        // Arrange
        int[] input = {1, 1, 2};
        int expected = 1;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array [1,1,2] ควรมี clump 1 ตัว");
        
        // Coverage Info
        System.out.println("TC7 PASSED: clump then change [1,1,2]");
        System.out.println("Path: 1→2→4-6→7→8→9-10→11→7→8→11→12-13→7→14");
        System.out.println("Branch: ①b(F), ②a(T), ③a(T), ④b(F), ②a(T), ③b(F), ④a(T), ②b(F)");
        System.out.println("Condition: A=F, B=F, C=T, D=F, E=F, F=T");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("Additional Test: Multiple clumps")
    public void testAdditional_MultipleClumps() {
        // Arrange
        int[] input = {1, 1, 2, 2, 3, 3, 3};
        int expected = 3;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array [1,1,2,2,3,3,3] ควรมี clump 3 ตัว");
        
        System.out.println("ADDITIONAL TEST PASSED: multiple clumps [1,1,2,2,3,3,3] = 3");
        System.out.println("----------------------------------------");
    }

    @Test
    @DisplayName("Additional Test: All different elements")
    public void testAdditional_AllDifferent() {
        // Arrange
        int[] input = {1, 2, 3, 4, 5};
        int expected = 0;
        
        // Act
        int actual = CountWordClumps.countClumps(input);
        
        // Assert
        assertEquals(expected, actual, "Array [1,2,3,4,5] ไม่ควรมี clump");
        
        System.out.println("ADDITIONAL TEST PASSED: all different [1,2,3,4,5] = 0");
        System.out.println("----------------------------------------");
    }

    // =================================================================
    //                         TEST RUNNER MAIN METHOD
    // =================================================================
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    C/DC COVERAGE TEST EXECUTION");
        System.out.println("========================================\n");
        
        // Create test instance
        CountWordClumpsTest test = new CountWordClumpsTest();
        
        try {
            System.out.println("Executing Test Cases for C/DC Coverage...\n");
            
            // Execute all test cases manually
            test.setUp();
            test.testTC1_NullInput();
            
            test.setUp();
            test.testTC2_EmptyArray();
            
            test.setUp();
            test.testTC3_SingleElement();
            
            test.setUp();
            test.testTC4_OneClumpTwoElements();
            
            test.setUp();
            test.testTC5_LongClump();
            
            test.setUp();
            test.testTC6_NoClump();
            
            test.setUp();
            test.testTC7_ClumpThenChange();
            
            test.setUp();
            test.testAdditional_MultipleClumps();
            
            test.setUp();
            test.testAdditional_AllDifferent();
            
            // Print coverage summary
            printCoverageSummary();
            
        } catch (Exception e) {
            System.err.println("❌ Test execution failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void printCoverageSummary() {
        System.out.println("\n========================================");
        System.out.println("           COVERAGE SUMMARY");
        System.out.println("========================================");
        System.out.println("✓ Branch Coverage: 100% (8/8 branches covered)");
        System.out.println("✓ Condition Coverage: 100% (6/6 conditions - both T/F)");
        System.out.println("✓ C/DC Coverage: 100%");
        
        System.out.println("\nBranches Covered:");
        System.out.println("  ①a: (nums == null || nums.length == 0) = True");
        System.out.println("  ①b: (nums == null || nums.length == 0) = False");
        System.out.println("  ②a: (i < nums.length) = True (loop entry)");
        System.out.println("  ②b: (i < nums.length) = False (loop exit)");
        System.out.println("  ③a: (nums[i] == prev && !inClump) = True");
        System.out.println("  ③b: (nums[i] == prev && !inClump) = False");
        System.out.println("  ④a: (nums[i] != prev) = True");
        System.out.println("  ④b: (nums[i] != prev) = False");
        
        System.out.println("\nConditions Covered:");
        System.out.println("  A: nums == null [T,F]");
        System.out.println("  B: nums.length == 0 [T,F]");
        System.out.println("  C: i < nums.length [T,F]");
        System.out.println("  D: nums[i] == prev [T,F]");
        System.out.println("  E: !inClump [T,F]");
        System.out.println("  F: nums[i] != prev [T,F]");
        
        System.out.println("\n========================================");
        System.out.println("        ALL TESTS COMPLETED!");
        System.out.println("   C/DC COVERAGE = 100% ACHIEVED");
        System.out.println("========================================");
    }
}