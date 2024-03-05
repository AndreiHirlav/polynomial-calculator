package Interface;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Interfata extends JFrame{
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
        JTextArea input1 = new JTextArea(1, 10);
        JTextArea input2 = new JTextArea(1, 10);
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
        Border buttonBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        JButton addBtn = new JButton("Add");
        addBtn.setFont(buttonFont);
        addBtn.setBorder(buttonBorder);
        JButton subBtn = new JButton("Sub");
        subBtn.setFont(buttonFont);
        subBtn.setBorder(buttonBorder);
        JButton mulBtn = new JButton("Multiply");
        mulBtn.setFont(buttonFont);
        mulBtn.setBorder(buttonBorder);
        JButton divBtn = new JButton("Divide");
        divBtn.setFont(buttonFont);
        divBtn.setBorder(buttonBorder);
        JButton derBtn = new JButton("Derive");
        derBtn.setFont(buttonFont);
        derBtn.setBorder(buttonBorder);
        JButton intBtn = new JButton("Integrate");
        intBtn.setFont(buttonFont);
        intBtn.setBorder(buttonBorder);

        buttonPanel.add(addBtn);
        buttonPanel.add(subBtn);
        buttonPanel.add(mulBtn);
        buttonPanel.add(divBtn);
        buttonPanel.add(derBtn);
        buttonPanel.add(intBtn);
        buttonPanel.setBackground(new Color(146, 190, 229));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return buttonPanel;
    }

    public JPanel configureResultPanel(){
        //results Panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        JTextArea resultsArea = new JTextArea(5, 20);
        resultsArea.setEditable(false);
        resultsPanel.setBackground(new Color(176, 213, 239));
        resultsArea.setBackground(new Color(144, 187, 211));
        resultsPanel.add(new JLabel("Results:"), BorderLayout.NORTH);
        resultsPanel.add(resultsArea, BorderLayout.CENTER);
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return resultsPanel;
    }
}
