package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
            ArrayList<Relation> relations = (ArrayList<Relation>) dicoDonnees.get("relation");
            Graphe grapheRecherche = grapheLogique.rechercheLienNoeud(noeudRecherche, relations);
            grapheRecherche.updateVisualGraph(grapheVisuel, relations);
        } else if (dicoDonnees.get("typeRecherche").equals("Parcours selon une relation")) {
            Noeud noeudRecherche = (Noeud) dicoDonnees.get("noeudRecherche");
            ArrayList<Relation> relations = (ArrayList<Relation>) dicoDonnees.get("relation");
            Graphe grapheRecherche = grapheLogique.rechercheTouteRelation(relations.get(0).toString(), noeudRecherche,
                    new Graphe());
            grapheRecherche.updateVisualGraph(grapheVisuel);
        }

    }
}
