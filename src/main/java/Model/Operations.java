package Model;
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

    public static HashMap<Integer, Double> multiplication(Polynomial poly1, Polynomial poly2) {
        HashMap<Integer, Double> rezultat = new HashMap<>();

        for(Map.Entry<Integer, Double> polyn1: poly1.getMonomials().entrySet())
            for(Map.Entry<Integer, Double> polyn2 : poly2.getMonomials().entrySet()) {
                int power = polyn1.getKey() +polyn2.getKey();
                double coeficient = polyn1.getValue() * polyn2.getValue();

                if(!rezultat.containsKey(power)) { //daca nu contine cheia=puterea noua
                    rezultat.put(power, coeficient);
                } else {
                    rezultat.put(power, coeficient + rezultat.get(power));   //adaug coeficientul nou obtinut altfel
                }
            }


        return rezultat;
    }

    public static HashMap<Integer, Double>divide(Polynomial poly1, Polynomial poly2) {
        //iau polinoamele si le sortez
        TreeMap<Integer, Double> poly1Tree = new TreeMap<>(poly1.getMonomials());
        TreeMap<Integer, Double> poly1Ordered = new TreeMap<>(poly1Tree.descendingMap());

        TreeMap<Integer, Double> poly2Tree = new TreeMap<>(poly2.getMonomials());
        TreeMap<Integer, Double> poly2Ordered = new TreeMap<>(poly2Tree.descendingMap());

        HashMap<Integer, Double> quotient = new HashMap<>();

       //cat timp mai am termeni in primul polinom si gradul sau e mai mare decat gradul la al doilea
        while(!poly1Ordered.isEmpty() && poly1Ordered.firstKey() >= poly2Ordered.firstKey()) {
            //impart primul monom
            int degree = poly1Ordered.firstKey() - poly2Ordered.firstKey();
            double coefficient = poly1Ordered.firstEntry().getValue() / poly2Ordered.firstEntry().getValue();

            quotient.put(degree, coefficient);

            //inmultesc polinomul 2 cu rezultatul impartirii
            for(Map.Entry<Integer, Double> entry : poly2Ordered.entrySet()) {
                int newDegree = entry.getKey() + degree;
                double newCoeff = entry.getValue() * coefficient;

                if(poly1Ordered.containsKey(newDegree)) {
                    //daca am puterea deja adaugata fac scaderea
                    double updatedCoeff = poly1Ordered.get(newDegree) - newCoeff;
                    if(updatedCoeff == 0) {
                        poly1Ordered.remove(newDegree);
                    } else {
                        poly1Ordered.put(newDegree, updatedCoeff);
                    }
                } else {
                    //altfel o adaug cu -
                    poly1Ordered.put(newDegree, -newCoeff);
                }
            }
        }
        poly1.setMonomials(new HashMap<Integer, Double>(poly1Ordered));  //si pun restul in poly1
        return quotient;
    }

    public static HashMap<Integer, Double>derivation(Polynomial poly1) {
        HashMap<Integer, Double> rezultat = new HashMap<>();

        for(Map.Entry<Integer, Double> entry : poly1.getMonomials().entrySet()) {
            if(entry.getKey() == 1) {   //adica am de forma b*X
                rezultat.put(0, entry.getValue());
            } else if(entry.getKey() > 1) {   //asta e cazul general
                rezultat.put(entry.getKey() - 1, entry.getValue() * entry.getKey());   //inmultesc puterea cu coeficientul, dupa scad puterea
            }
        }

        return rezultat;
    }

    public static HashMap<Integer, Double>integration(Polynomial poly1) {
        HashMap<Integer, Double> rezultat = new HashMap<>();

        for(Map.Entry<Integer, Double> entry : poly1.getMonomials().entrySet()) {
            rezultat.put(entry.getKey() + 1, entry.getValue()/ (entry.getKey() + 1));
        }

        return rezultat;
    }



}
