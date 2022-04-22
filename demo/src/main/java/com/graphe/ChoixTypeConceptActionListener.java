package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;

public class ChoixTypeConceptActionListener implements ActionListener {

    private JComboBox<String> combo;
    private JPanel panelChoixCreerNoeudConteneur;
    private JPanelAvecTypeNoeud panelChoixCreerNoeudCourant;
    private JButton boutonCreerNoeud;
    private Graph graph;
    private Graphe g;

    public ChoixTypeConceptActionListener(JComboBox combo, JPanel panelChoixCreerNoeudConteneur,
            JPanelAvecTypeNoeud panelChoixCreerNoeudCourant, JButton boutonCreerNoeud, Graph graph, Graphe g) {
        this.combo = combo;
        this.panelChoixCreerNoeudConteneur = panelChoixCreerNoeudConteneur;
        this.panelChoixCreerNoeudCourant = panelChoixCreerNoeudCourant;
        this.boutonCreerNoeud = boutonCreerNoeud;
        this.graph = graph;
        this.g = g;
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
                break;
            case "Instance":
                panelChoixCreerNoeudCourant = new JPanelInstance();
                break;
            case "Attribut":
                panelChoixCreerNoeudCourant = new JPanelAttribut();
            default:
                break;
        }
        panelChoixCreerNoeudConteneur.removeAll();
        panelChoixCreerNoeudConteneur.add(panelChoixCreerNoeudCourant);
        panelChoixCreerNoeudConteneur.revalidate();
        panelChoixCreerNoeudConteneur.repaint();
        panelChoixCreerNoeudConteneur.setMaximumSize(panelChoixCreerNoeudConteneur.getPreferredSize());

        for (ActionListener al : boutonCreerNoeud.getActionListeners()) {
            boutonCreerNoeud.removeActionListener(al);
        }
        boutonCreerNoeud.addActionListener(new CreerNoeudActionListener(panelChoixCreerNoeudCourant, g, graph));
    }

}