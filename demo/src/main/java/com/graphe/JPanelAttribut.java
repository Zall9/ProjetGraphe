package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

class JPanelAttribut extends JPanel {

    private JLabel attributLabel;
    private JTextField textfieldInput;
    public JPanelAttribut() {
        super();
        GridLayout grid = new GridLayout(0, 2);
        setLayout(grid);
        //JTextField textfieldInput= new JTextField("Entrez la valeur de l'attribut");
        textfieldInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField tf = (JTextField) e.getSource();
                System.out.println(tf.getText());
            }
        });

        // Initialisation du label du type de concept
        attributLabel = new JLabel("Valeur:  ");

        // Ajout des composants au panel
        this.add(attributLabel);
        this.add(textfieldInput);
    }

}