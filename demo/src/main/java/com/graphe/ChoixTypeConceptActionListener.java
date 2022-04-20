package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ChoixTypeConceptActionListener implements ActionListener {

    private JComboBox<String> combo;
    private JPanel panelChoixCreerNoeudConteneur;
    private JPanel panelChoixCreerNoeudCourant;

    public ChoixTypeConceptActionListener(JComboBox combo, JPanel panelChoixCreerNoeudConteneur,
            JPanel panelChoixCreerNoeudCourant) {
        this.combo = combo;
        this.panelChoixCreerNoeudConteneur = panelChoixCreerNoeudConteneur;
        this.panelChoixCreerNoeudCourant = panelChoixCreerNoeudCourant;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String typeNoeud = combo.getSelectedItem().toString();
        System.out.println(typeNoeud);
        changePanel(typeNoeud);

    }

    public void changePanel(String typeNoeud) {
        switch (typeNoeud) {
            case "Concept":
                panelChoixCreerNoeudCourant = new JPanelConcept();
                panelChoixCreerNoeudConteneur.removeAll();
                panelChoixCreerNoeudConteneur.add(panelChoixCreerNoeudCourant);
                panelChoixCreerNoeudConteneur.revalidate();
                panelChoixCreerNoeudConteneur.repaint();
                break;
            case "Instance":
                // TODO: Créer JPanelInstance
                panelChoixCreerNoeudCourant = new JPanel();
                panelChoixCreerNoeudCourant.setBackground(Color.red);
                panelChoixCreerNoeudConteneur.removeAll();
                panelChoixCreerNoeudConteneur.add(panelChoixCreerNoeudCourant);
                panelChoixCreerNoeudConteneur.revalidate();
                panelChoixCreerNoeudConteneur.repaint();
                break;
            case "Attribut":
                // TODO: Créer JPanelAttribut
                panelChoixCreerNoeudCourant = new JPanel();
                panelChoixCreerNoeudCourant.setBackground(Color.green);
                panelChoixCreerNoeudConteneur.removeAll();
                panelChoixCreerNoeudConteneur.add(panelChoixCreerNoeudCourant);
                panelChoixCreerNoeudConteneur.revalidate();
                panelChoixCreerNoeudConteneur.repaint();

            default:
                break;
        }
    }

}