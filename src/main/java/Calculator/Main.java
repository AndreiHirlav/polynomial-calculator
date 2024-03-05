package Calculator;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String p1 = "3X^5 + 2x + 5";
        String p2 = "-X^2 +4X - 3";

        HashMap<Integer, Double> cf = Polynomial.parsePolynomial(p1);
        Polynomial poly = new Polynomial();
        poly.setMonomials(cf);

        for(Map.Entry<Integer, Double> entry: poly.getMonomials().entrySet()) {
            System.out.println("Puterea " + entry.getKey() + " coeficientul " + entry.getValue());
        }
    }
}
