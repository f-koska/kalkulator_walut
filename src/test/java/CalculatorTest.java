import main.java.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {


    @Test
    void getValueOfCurrencyDoesntExist() throws Exception {
        Calculator calculator = new Calculator(123, "ABC");
        Exception exception = assertThrows(Exception.class, calculator::getValueOfCurrencyConversion);
        assertTrue(exception.getMessage().contains(Calculator.ExceptionBadCurrency));
    }

    @Test
    void setNegativeValue() throws Exception {
        Calculator calculator = new Calculator(123, "USD");
        Exception exception = assertThrows(Exception.class, () ->calculator.setValue(-2));
        assertEquals(Calculator.ExceptionNegativeNumber, exception.getMessage());
    }
    @Test
    void createObjectWithNegativeValue() throws Exception {
        Exception exception = assertThrows(Exception.class,() -> new Calculator(-123, "USD"));
        assertEquals(Calculator.ExceptionNegativeNumber, exception.getMessage());
    }

    @Test
    void getValueOfCurrencyWithCorrectParameters() throws Exception {
        Calculator calculator = new Calculator(2, "USD");
        assertDoesNotThrow(calculator::getValueOfCurrencyConversion);

    }

}