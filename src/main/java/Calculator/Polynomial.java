package Calculator;

import java.util.*;
import java.util.regex.*;

public class Polynomial {
    private HashMap<Integer, Double> monomials;

    public Polynomial() {
        this.monomials = new HashMap<>();
    }

    public static HashMap<Integer, Double> parsePolynomial(String polynomial) {
        String normalizedPoly = polynomial.replaceAll("\\s", "").replaceAll("\\-", "+-");
        //partea asta elimina whitespace-urile: \s si inlocuieste semnele - (\-) cu +- pentru standardizare

        Pattern pattern = Pattern.compile("(?i)([+-]?\\d*)x\\^([0-9]+)|([+-]?\\d+)x|([+-]?\\d+)");
        //e case-insensitive datorita (?i), bucata urmatoare pana la | face matchingul cu un coeficent de la 0 la 9, si la fel si pentru
        //termenii la care avem x simplu, dupa doar la constante, \d inseamna digit de la 0 la 9
        Matcher matcher = pattern.matcher(normalizedPoly);

        HashMap<Integer, Double> coef = new HashMap<>();

        while(matcher.find()) {
            double coeficient = 1;    //coeficientul default
            int power = 0;         //puterea default

            if(matcher.group(1) != null) {     //adica daca am ax^n, se potriveste cu prima forma din regex
                String coefStr = matcher.group(1);
                coeficient = coefStr.equals("+") || coefStr.isEmpty() ? 1 : coefStr.equals("-") ? -1 : Integer.parseInt(coefStr);
                power = Integer.parseInt(matcher.group(2));
            } else if (matcher.group(3) != null) {   //adica am bx
                coeficient = Integer.parseInt(matcher.group(3));
                power = 1;
            } else if (matcher.group(4) != null) {   //adica am doar constanta
                coeficient = Integer.parseInt(matcher.group(4));
                power = 0;
            }
            coef.put(power, coeficient);
        }
        return coef;
    }

    public void setMonomials(HashMap<Integer, Double> monomials) {
        this.monomials = monomials;
    }

    public HashMap<Integer, Double> getMonomials() {
        return monomials;
    }
}
