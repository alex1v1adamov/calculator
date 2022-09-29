package com.demo.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class CalculatorApplicationTests {

    @Autowired
    private CalcService calcService;


    @Test
    void testCase1() {
        Optional<String> evaluate = calcService.evaluate("1+1");
        assertTrue(evaluate.isPresent());
    }

    @Test
    void testCase2() {
        Optional<String> evaluate = calcService.evaluate("wrong");
        assertFalse(evaluate.isPresent());
    }

    @Test
    void testCase3() {
        Optional<String> evaluate = calcService.evaluate("cos(0)");
        assertTrue(evaluate.isPresent());
        assertEquals("1.0", evaluate.get());
    }

    @Test
    void testCase4() {
        Optional<String> evaluate = calcService.evaluate("sqrt(64)");
        assertTrue(evaluate.isPresent());
        assertEquals("8.0", evaluate.get());
    }

    @Test
    void testCase5() {
        Optional<String> evaluate = calcService.evaluate("-5+6*6-10");
        assertFalse(evaluate.isPresent());
        assertEquals("21", evaluate.get());
    }

    @Test
    void testCase6() {
        Optional<String> evaluate = calcService.evaluate("(-5+6)*6-10");
        assertFalse(evaluate.isPresent());
        assertEquals("-4", evaluate.get());
    }
}
