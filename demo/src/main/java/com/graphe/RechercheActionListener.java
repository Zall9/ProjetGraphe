package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import org.graphstream.graph.Graph;

public class RechercheActionListener implements ActionListener {

    private Graphe grapheLogique;
    private Graph grapheVisuel;
    private Map<String, Object> dicoDonnees;

    public RechercheActionListener(Graphe grapheLogique, Graph grapheVisuel, Map<String, Object> dicoDonnees) {
        this.grapheLogique = grapheLogique;
        this.grapheVisuel = grapheVisuel;
        this.dicoDonnees = dicoDonnees;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (dicoDonnees.get("typeRecherche").equals("Instances d'un Concept")) {
            System.out.println(dicoDonnees.get("concept"));
            boolean afficherAttributs = dicoDonnees.get("afficherAttributs").equals("true");
            Graphe grapheRecherche = grapheLogique.rechercheInstancesDeConcept(dicoDonnees.get("concept").toString(),
                    afficherAttributs);
            grapheRecherche.updateVisualGraph(grapheVisuel);
        } else if (dicoDonnees.get("typeRecherche").equals("Afficher le graphe principal")) {
            grapheLogique.updateVisualGraph(grapheVisuel);
        } else if (dicoDonnees.get("typeRecherche").equals("Noeuds selon relation")) {
            Noeud noeudRecherche = (Noeud) dicoDonnees.get("noeudRecherche");
            Relation relation = (Relation) dicoDonnees.get("relation");
            Graphe grapheRecherche = grapheLogique.rechercheLienNoeud(noeudRecherche, relation);
            grapheRecherche.updateVisualGraph(grapheVisuel);
        }
    }

}