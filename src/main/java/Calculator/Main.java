package Calculator;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String p1 = "4X^5 -3*X^4 + X^2 - 8*X + 1";
        String p2 = "3*X^4 - X^3 + X^2 + 2*X - 1";

        HashMap<Integer, Double> m1 = Polynomial.parsePolynomial(p1);
        HashMap<Integer, Double> m2 = Polynomial.parsePolynomial(p2);
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();
        poly1.setMonomials(m1);
        poly2.setMonomials(m2);

        System.out.println("Primul polinom: ");
        for(Map.Entry<Integer, Double> entry: poly1.getMonomials().entrySet()) {
            System.out.println("Puterea " + entry.getKey() + " coeficientul " + entry.getValue());
        }

        System.out.println("Al doilea polinom:");
        for(Map.Entry<Integer, Double> entry: poly2.getMonomials().entrySet()) {
            System.out.println("Puterea " + entry.getKey() + " coeficientul " + entry.getValue());
        }

        System.out.println("\nRezultat adunare: ");
        Polynomial poly3 = new Polynomial();
        HashMap<Integer, Double> m3 = Operations.addition(poly1, poly2);
        poly3.setMonomials(m3);
        for(Map.Entry<Integer, Double> entry: poly3.getMonomials().entrySet()) {
            System.out.println("Puterea " + entry.getKey() + " coeficientul " + entry.getValue());
        }

        System.out.println("\nRezultat scadere: ");
        Polynomial poly4 = new Polynomial();
        HashMap<Integer, Double> m4 = Operations.subtraction(poly1, poly2);
        poly4.setMonomials(m4);
        for(Map.Entry<Integer, Double> entry: poly4.getMonomials().entrySet()) {
            System.out.println("Puterea " + entry.getKey() + " coeficientul " + entry.getValue());
        }


    }
}
