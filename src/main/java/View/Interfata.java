package View;
import Model.Operations;
import Model.Polynomial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class Interfata extends JFrame{

    private JTextArea input1 = new JTextArea(1, 10);
    private JTextArea input2 = new JTextArea(1, 10);
    private JTextArea resultsArea = new JTextArea(5, 20);
    public Interfata() {
        this.setSize(400, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Polynomial Calculator");
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.add(configureTitlePanel());
        this.add(configureInputPanel());
        this.add(configureButtonPanel());
        this.add(configureResultPanel());
        this.setVisible(true);
    }

    public JPanel configureTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Polynomial Calculator");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(162, 226, 231));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return titlePanel;
    }
    public JPanel configureInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Enter first polynomial:"));
        inputPanel.add(input1);
        inputPanel.add(new JLabel("Enter second polynomial:"));
        inputPanel.add(input2);
        inputPanel.setBackground(new Color(183, 225, 238));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return inputPanel;
    }

    public JPanel configureButtonPanel() {
        //buttons Panel
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        JButton addBtn = new JButton("Add");
        addBtn.setFont(buttonFont);
        JButton subBtn = new JButton("Sub");
        subBtn.setFont(buttonFont);

        JButton mulBtn = new JButton("Multiply");
        mulBtn.setFont(buttonFont);
        JButton divBtn = new JButton("Divide");
        divBtn.setFont(buttonFont);
        JButton derBtn = new JButton("Derive");
        derBtn.setFont(buttonFont);
        JButton intBtn = new JButton("Integrate");
        intBtn.setFont(buttonFont);
        buttonPanel.add(addBtn);
        buttonPanel.add(subBtn);
        buttonPanel.add(mulBtn);
        buttonPanel.add(divBtn);
        buttonPanel.add(derBtn);
        buttonPanel.add(intBtn);
        buttonPanel.setBackground(new Color(146, 190, 229));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addListeners(addBtn, subBtn, mulBtn, divBtn, derBtn, intBtn);
        return buttonPanel;
    }

    public JPanel configureResultPanel(){
        //results Panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsArea.setEditable(false);
        resultsPanel.setBackground(new Color(176, 213, 239));
        resultsArea.setBackground(new Color(144, 187, 211));
        resultsPanel.add(new JLabel("Results:"), BorderLayout.NORTH);
        resultsPanel.add(resultsArea, BorderLayout.CENTER);

        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        notePanel.setBackground(new Color(176, 213, 239));
        JLabel noteLabel1 = new JLabel("Note: For the derivative/integration operations,");
        JLabel noteLabel2 = new JLabel("only the first polynomial will be used.");
        notePanel.add(noteLabel1);
        notePanel.add(noteLabel2);
        resultsPanel.add(notePanel, BorderLayout.SOUTH);
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return resultsPanel;
    }

    public void addListeners(JButton add, JButton sub, JButton mul, JButton div, JButton der, JButton intgr) {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("add");
            }
        });

        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("sub");
            }
        });

        mul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("mul");
            }
        });
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("div");
            }
        });

        der.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("der");
            }
        });

        intgr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("intgr");
            }
        });
    }

    public void performOperation(String operation) {
        Polynomial poly1 = new Polynomial();
        poly1.setMonomials(Polynomial.parsePolynomial(input1.getText()));
        Polynomial poly2 = new Polynomial();
        poly2.setMonomials(Polynomial.parsePolynomial(input2.getText()));
        HashMap<Integer, Double> result = null;

        switch (operation) {
            case "add" :
                result = Operations.addition(poly1, poly2);
                resultsArea.setText(new String(Polynomial.hashToString(result)));
                break;
            case "sub" :
                result = Operations.subtraction(poly1, poly2);
                resultsArea.setText(new String(Polynomial.hashToString(result)));
                break;
            case "mul" :
                result = Operations.multiplication(poly1, poly2);
                resultsArea.setText(new String(Polynomial.hashToString(result)));
                break;
            case "div" :
                try {
                    result = Operations.divide(poly1, poly2);
                    resultsArea.setText(new String(Polynomial.hashToString(result)));
                }catch(ArithmeticException e) {
                    resultsArea.setText("Cannot divide by 0!");
                }
                break;
            case "der" :
                result = Operations.derivation(poly1);
                resultsArea.setText(new String(Polynomial.hashToString(result)));
                break;
            case "intgr" :
                result = Operations.integration(poly1);
                resultsArea.setText(new String(Polynomial.hashToString(result) + " + C"));
                break;
        }
    }
}
