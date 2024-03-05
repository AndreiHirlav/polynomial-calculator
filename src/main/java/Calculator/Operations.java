package Calculator;
import java.util.*;

public class Operations {

    public static HashMap<Integer, Double>addition(Polynomial poly1, Polynomial poly2) {
        HashMap<Integer, Double> rezultat = new HashMap<>();

        //pun primul polinom in rezultat
        for(Integer key : poly1.getMonomials().keySet()) {
            rezultat.put(key, poly1.getMonomials().get(key));
        }

        //adaug al doilea
        for(Integer key : poly2.getMonomials().keySet()) {
            if(!rezultat.containsKey(key)) {   //daca nu am puterea atunci o adaug pur si simplu
                rezultat.put(key, poly2.getMonomials().get(key));
            } else {
                double valoare = poly1.getMonomials().get(key) + poly2.getMonomials().get(key); //altfel adun valorile
                rezultat.put(key, valoare);  //si le bag in rezultat
            }
        }

        return rezultat;
    }

    public static HashMap<Integer, Double>subtraction(Polynomial poly1, Polynomial poly2) {
        HashMap<Integer, Double> rezultat = new HashMap<>();

        //pun primul polinom in rezultat
        for(Integer key : poly1.getMonomials().keySet()) {
            rezultat.put(key, poly1.getMonomials().get(key));
        }

        //scad al doilea
        for(Integer key : poly2.getMonomials().keySet()) {
            if(!rezultat.containsKey(key)) {   //daca nu am puterea atunci o adaug pur si simplu
                rezultat.put(key, -poly2.getMonomials().get(key));
            } else {
                double valoare = poly1.getMonomials().get(key) - poly2.getMonomials().get(key); //altfel scad valorile
                rezultat.put(key, valoare);  //si le bag in rezultat
            }
        }

        return rezultat;
    }

}
