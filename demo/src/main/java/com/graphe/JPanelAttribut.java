package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

class JPanelAttribut extends JPanelAvecTypeNoeud {

    private JLabel attributLabel;
    private JTextField textfieldInput;

    public JPanelAttribut() {
        super();
        setTypeNoeud("Attribut");
        GridLayout grid = new GridLayout(0, 2);
        setLayout(grid);
        textfieldInput = new JTextField("");
        textfieldInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textfieldInput.getText());
            }
        });

        // Initialisation du label du type de concept
        attributLabel = new JLabel("Valeur:  ");

        // Ajout des composants au panel
        this.add(attributLabel);
        this.add(textfieldInput);
    }

    public String getValAttribut() {
        return textfieldInput.getText();
    }

}