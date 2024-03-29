package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.graphstream.graph.Graph;

public class CreerNoeudActionListener implements ActionListener {

    private JPanelAvecTypeNoeud panelChoixCreerNoeudCourant;
    private Graphe grapheLogique;
    private Graph grapheVisuel;
    private JPanelRelation panelRelation;

    public CreerNoeudActionListener(JPanelAvecTypeNoeud panelChoixCreerNoeudCourant, Graphe grapheLogique,
            Graph grapheVisuel, JPanelRelation panelRelation) {
        this.panelRelation = panelRelation;
        this.panelChoixCreerNoeudCourant = panelChoixCreerNoeudCourant;
        this.grapheLogique = grapheLogique;
        this.grapheVisuel = grapheVisuel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String type = panelChoixCreerNoeudCourant.getTypeNoeud();
        if (type.equals("Concept")) {
            JPanelConcept panel = (JPanelConcept) panelChoixCreerNoeudCourant;
            // Creation du noeud
            ConceptNoeud nouveauNoeudConcept = new ConceptNoeud(panel.getTypeConcept(), panel.getNomConcept());
            // Ajout du noeud au graphe logique
            grapheLogique.ajouterNoeud(nouveauNoeudConcept);
            // Ajout du noeud au graphe visuel
            grapheVisuel.addNode(nouveauNoeudConcept.getId());
            System.out.println("Noeud ajouté au graphe visuel " + nouveauNoeudConcept);
            grapheVisuel.getNode(nouveauNoeudConcept.getId()).setAttribute("ui.label", nouveauNoeudConcept.toString());
            grapheVisuel.getNode(nouveauNoeudConcept.getId()).setAttribute("ui.class",
                    nouveauNoeudConcept.getTypeNoeud());
            // Ajout du nouveau noeud aux combobox du panel relation
            panelRelation.getComboNoeudDroit().addItem(nouveauNoeudConcept);
            panelRelation.getComboNoeudGauche().addItem(nouveauNoeudConcept);

        } else if (type.equals("Instance")) {
            JPanelInstance panel = (JPanelInstance) panelChoixCreerNoeudCourant;
            InstanceNoeud nouveauNoeudInstance = new InstanceNoeud(panel.getNomInstance());

            // Ajout du noeud au graphe logique
            grapheLogique.ajouterNoeud(nouveauNoeudInstance);
            grapheVisuel.addNode(nouveauNoeudInstance.getId());
            System.out.println("noeud ajouté a graph " + nouveauNoeudInstance);
            grapheVisuel.getNode(nouveauNoeudInstance.getId()).setAttribute("ui.label",
                    nouveauNoeudInstance.toString());
            grapheVisuel.getNode(nouveauNoeudInstance.getId()).setAttribute("ui.class",
                    nouveauNoeudInstance.getTypeNoeud());
            // Ajout du nouveau noeud aux combobox du panel relation
            panelRelation.getComboNoeudDroit().addItem(nouveauNoeudInstance);
            panelRelation.getComboNoeudGauche().addItem(nouveauNoeudInstance);

        } else if (type.equals("Attribut")) {
            JPanelAttribut panel = (JPanelAttribut) panelChoixCreerNoeudCourant;
            AttributNoeud nouveauNoeudAttribut = new AttributNoeud(panel.getValAttribut());

            // Ajout du noeud au graphe logique
            grapheLogique.ajouterNoeud(nouveauNoeudAttribut);
            grapheVisuel.addNode(nouveauNoeudAttribut.getId());
            System.out.println("noeud ajouté a graph " + nouveauNoeudAttribut);
            grapheVisuel.getNode(nouveauNoeudAttribut.getId()).setAttribute("ui.label",
                    nouveauNoeudAttribut.toString());
            grapheVisuel.getNode(nouveauNoeudAttribut.getId()).setAttribute("ui.class",
                    nouveauNoeudAttribut.getTypeNoeud());

            // Ajout du nouveau noeud aux combobox du panel relation
            panelRelation.getComboNoeudDroit().addItem(nouveauNoeudAttribut);
            panelRelation.getComboNoeudGauche().addItem(nouveauNoeudAttribut);
        }

        panelRelation.revalidate();
        panelRelation.repaint();
        panelRelation.setMaximumSize(panelRelation.getPreferredSize());

    }
}