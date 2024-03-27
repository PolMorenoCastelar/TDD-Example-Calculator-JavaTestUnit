package com.tecnocampus.ES3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class CalculatorTest {

    private final String expression;
    private final String expectedDisplay;
    private final Calculator calculator;

    public CalculatorTest(String expression, String expectedDisplay) {
        this.expression = expression;
        this.expectedDisplay = expectedDisplay;
        this.calculator = new Calculator();
    }

    @Parameters(name = "{index}: {0} = {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"", ""},
               {"0", "0"} ,
               {"1", "1"},
               {"56", "56"},
               {"0!", "1"},
               {"1!", "1"},
               {"2!", "2"},
               {"3!", "6"},
               {"4!", "24"},
               {"9!", "362880"},
                {"78=", "78"},
               {"0+0=", "0"},
              {"0+3=", "3"},
               {"3+4=", "7"},
               {"12+34=", "46"},
               {"1+2+", "3"},
               {"1+2+4=", "7"},
               {"0*0=", "0"},
               {"3*0=", "0"},
               {"3*4=", "12"},
               {"12*34=", "408"},
               {"12*", "12"},
                {"12*34", "34"},
               {"12*34+", "408"},
               {"2*3*4=", "24"},
               {"2+3+4=", "9"},
              {"2+3*", "3"},
               {"4*3+2=", "14"},
               {"4*3+", "12"},
               {"1+2+3*4=", "15"},
               {"4*3+2+1=", "15"},
               {"2+3*4+5=", "19"},
               {"2+3!=", "8"},
               {"2+3!", "6"},
               {"2*3!=", "12"},
               {"2*3!", "6"},
               {"5!+3!*4!=", "264"},
        });
    }

    @Test
    public void test() {

        // functional parsing
        expression.chars()
                .mapToObj(i -> (char) i)
                .forEach(calculator::processToken);

        // old-school parsing. Equivalent to previous one
        /* for (char token: expression.toCharArray()) {
            calculator.processToken(token);
        } */

        assertEquals(expectedDisplay, calculator.getDisplay());
    }

}
