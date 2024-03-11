import Model.Operations;
import Model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OperationTest {
    Polynomial poly1 = new Polynomial("3*X^2 -X + 1");
    Polynomial poly2 = new Polynomial("X-2");

    @Test
    public void additionTest() {
        HashMap<Integer, Double> result = Operations.addition(poly1, poly2);
        assertEquals(3, result.get(2));
        assertEquals(0, result.get(1));
        assertEquals(-1, result.get(0));
    }

    @Test
    public void subtractionTest() {
        HashMap<Integer, Double> result = Operations.subtraction(poly1, poly2);
        assertEquals(3, result.get(2));
        assertEquals(-2, result.get(1));
        assertEquals(3, result.get(0));
    }

    @Test
    public void multiplicationTest() {
        HashMap<Integer, Double> result = Operations.multiplication(poly1, poly2);
        assertEquals(3, result.get(3));
        assertEquals(-7, result.get(2));
        assertEquals(3, result.get(1));
        assertEquals(-2, result.get(0));
    }

    @Test
    public void derivationTest() {
        HashMap<Integer, Double> result = Operations.derivation(poly1);
        assertEquals(6, result.get(1));
        assertEquals(-1, result.get(0));
    }

    @Test
    public void integrationTest() {
        HashMap<Integer, Double> result = Operations.integration(poly1);
        assertEquals(1, result.get(3), 0.005);
        assertEquals(-0.5, result.get(2), 0.005);
        assertEquals(1, result.get(1), 0.005);
    }
}
