package Calculator;

import java.sql.SQLOutput;
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

        Pattern pattern = Pattern.compile("(?i)([+-]?(?:\\d+(?:\\.\\d+)?)?)\\*?x(?:\\^([0-9]+))?|([+-]?\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(normalizedPoly);

        HashMap<Integer, Double> coef = new HashMap<>();

        while (matcher.find()) {
            double coefficient = 1; //coeficientul default
            int power = 0; // puterea default

            if (matcher.group(1) != null) {
                String coefStr = matcher.group(1).isEmpty() ? "+1" : matcher.group(1); //aici tratez cazul in care nu e specificat coeficientul
                coefficient = coefStr.equals("+") ? 1 : coefStr.equals("-") ? -1 : Double.parseDouble(coefStr);
                power = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 1; //folosesc exponentul daca am, altfel 1
            } else if (matcher.group(3) != null) { //aici am o constata
                coefficient = Double.parseDouble(matcher.group(3));
            }

            coef.put(power, coef.containsKey(power) ? coef.get(power) + coefficient : coefficient);
        }
        return coef;
    }

    public static String hashToString(HashMap<Integer, Double> polynomial) {
        TreeMap<Integer, Double> sortedPoly = new TreeMap<>(polynomial); //imi sorteaza dupa cheie=exponent

        String result = "";

        for(Map.Entry<Integer, Double> entry : sortedPoly.descendingMap().entrySet()) {
            int exponent = entry.getKey();
            double coeficient = entry.getValue();

            if (coeficient == 0) {
                continue;   //adica nu trebuie sa adaug nimic
            }

            if (!result.isEmpty() && coeficient > 0) {
                result += " + ";
            } else if (coeficient < 0 && !result.isEmpty()) {
                result += " - ";
                coeficient = -coeficient;   //il fac pozitiv
            } else if (coeficient <= 0) {      //daca primul coeficient e negativ doar adaug semnul, altfel nu
                result += "-";
                coeficient = -coeficient;   //il fac pozitiv
            }

            if (Math.abs(coeficient) != 1 || exponent == 0) {   //adaug coeficientul daca e diferit de 1
                result += String.format("%.2f", coeficient);
            }

            if (exponent == 1) {
                if (Math.abs(coeficient) != 1)
                    result += "*X";
                else
                    result += "X";
            } else if (exponent > 1) {
                if (Math.abs(coeficient) != 1)
                    result += "*X^" + exponent;
                else
                    result += "X^" + exponent;
            }
        }



        return result;
    }

    public void setMonomials(HashMap<Integer, Double> monomials) {
        this.monomials = monomials;
    }

    public HashMap<Integer, Double> getMonomials() {
        return monomials;
    }
}
