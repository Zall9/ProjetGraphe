package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

class JPanelConcept extends JPanel {

    private JComboBox<String> typeConceptComboBox;
    private JLabel typeConceptLabel;

    public JPanelConcept() {
        super();
        GridLayout grid = new GridLayout(0, 2);
        setLayout(grid);
        // setBackground(Color.red);

        // Initialisation du comboBox du type de concept
        typeConceptComboBox = new JComboBox<String>();
        for (TypeConcept concept : TypeConcept.values()) {
            typeConceptComboBox.addItem(concept.name());
        }

        typeConceptComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                System.out.println(cb.getSelectedItem() + "");
            }
        });

        // Initialisation du label du type de concept
        typeConceptLabel = new JLabel("Type de concept : ");

        // Ajout des composants au panel
        this.add(typeConceptLabel);
        this.add(typeConceptComboBox);
    }

}