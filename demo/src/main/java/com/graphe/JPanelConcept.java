package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

class JPanelConcept extends JPanelAvecTypeNoeud {

    private JComboBox<String> typeConceptComboBox;
    private JLabel typeConceptLabel;
    private JTextField nomConcept;
    private JLabel nomConceptLabel;

    public JPanelConcept() {
        super();
        setTypeNoeud("Concept");
        GridLayout grid = new GridLayout(0, 2);
        setLayout(grid);
        // setBackground(Color.red);
        nomConcept = new JTextField();
        // Initialisation du comboBox du type de concept
        typeConceptComboBox = new JComboBox<String>();
        for (TypeConcept concept : TypeConcept.values()) {
            typeConceptComboBox.addItem(concept.name());
        }
        typeConceptComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(typeConceptComboBox.getSelectedItem() + "");
            }
        });

        nomConcept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(nomConcept.getText());
            }
        });
        // Initialisation du label du type de concept
        typeConceptLabel = new JLabel("Type de concept : ");
        nomConceptLabel = new JLabel("Nom du concept: ");
        // Ajout des composants au panel
        this.add(nomConceptLabel);
        this.add(nomConcept);
        this.add(typeConceptLabel);
        this.add(typeConceptComboBox);
    }

    public TypeConcept getTypeConcept() {
        return TypeConcept.valueOf(typeConceptComboBox.getSelectedItem() + "");
    }

    public String getNomConcept() {
        return nomConcept.getText();
    }

}