package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

class JPanelInstance extends JPanelAvecTypeNoeud {

    private JLabel instanceLabel;
    private JTextField textfieldInput;

    public JPanelInstance() {
        super();
        setTypeNoeud("Instance");
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
        instanceLabel = new JLabel("Valeur:  ");

        // Ajout des composants au panel
        this.add(instanceLabel);
        this.add(textfieldInput);
    }

    public String getNomInstance() {
        return textfieldInput.getText();
    }

}