package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test void letterGrade_95_isA() { assertEquals("A", calc.letterGrade(95)); }
    @Test void letterGrade_85_isB() { assertEquals("B", calc.letterGrade(85)); }
    @Test void gpa_95_and_85_is_3_5() { assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.0001); }
    @Test void scholarship_highGpaEnoughCredits_is100() { assertEquals(100, calc.scholarship(3.9, 16, false)); }
    @Test void scholarship_midGpaEnoughCredits_is70() { assertEquals(70, calc.scholarship(3.5, 13, false)); }

    @Test void letterGrade_negative_throws() { assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1)); }
    @Test void letterGrade_over100_throws() { assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101)); }
    @Test void letterGrade_75_isC() { assertEquals("C", calc.letterGrade(75)); }
    @Test void letterGrade_65_isD() { assertEquals("D", calc.letterGrade(65)); }
    @Test void letterGrade_30_isF() { assertEquals("F", calc.letterGrade(30)); }

    @Test void gpa_null_throws() { assertThrows(IllegalArgumentException.class, () -> calc.gpa(null)); }
    @Test void gpa_emptyArray_throws() { assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{})); }
    @Test void gpa_allGrades_includingF_is2() { assertEquals(2.0, calc.gpa(new double[]{95, 85, 75, 65, 30}), 0.0001); }

    @Test void scholarship_gpaNegative_throws() { assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false)); }
    @Test void scholarship_gpaAbove4_throws() { assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false)); }
    @Test void scholarship_creditsNegative_throws() { assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false)); }
    @Test void scholarship_violation_is0() { assertEquals(0, calc.scholarship(3.9, 16, true)); }
    @Test void scholarship_highGpaLowCredits_is70() { assertEquals(70, calc.scholarship(3.9, 14, false)); }
    @Test void scholarship_gpa34_lowCredits_is50() { assertEquals(50, calc.scholarship(3.5, 11, false)); }
    @Test void scholarship_gpa32_is50() { assertEquals(50, calc.scholarship(3.2, 20, false)); }
    @Test void scholarship_lowGpa_is0() { assertEquals(0, calc.scholarship(2.5, 20, false)); }
}
