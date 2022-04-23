package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.graphstream.graph.Graph;

public class BoutonCreerRelationAL implements ActionListener {

    private JPanelRelation panelRelation;
    private Graphe grapheLogique;
    private Graph grapheVisuel;

    public BoutonCreerRelationAL(JPanelRelation panelRelation, Graphe grapheLogique, Graph grapheVisuel) {
        this.panelRelation = panelRelation;
        this.grapheLogique = grapheLogique;
        this.grapheVisuel = grapheVisuel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Noeud noeudGauche = (Noeud) panelRelation.getComboNoeudGauche().getSelectedItem();
        Noeud noeudDroit = (Noeud) panelRelation.getComboNoeudDroit().getSelectedItem();
        TypeRelation typeRelation = (TypeRelation) panelRelation.getComboRelation().getSelectedItem();
        String nomRelation = panelRelation.getTextFieldNomRelation().getText();

        // Creation de la relation logique
        Relation r = panelRelation.createRelationLogique(noeudGauche, noeudDroit, typeRelation, nomRelation);

        // Creation de la relation visuelle
        panelRelation.createRelationVisuel(noeudGauche, noeudDroit, r);
    }

}
