package com.graphe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import org.graphstream.graph.Graph;

public class RechercheActionListener implements ActionListener {

    private Graphe grapheLogique;
    private Graph grapheVisuel;
    private Map<String, String> dicoDonnees;

    public RechercheActionListener(Graphe grapheLogique, Graph grapheVisuel, Map<String, String> dicoDonnees) {
        this.grapheLogique = grapheLogique;
        this.grapheVisuel = grapheVisuel;
        this.dicoDonnees = dicoDonnees;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (dicoDonnees.get("typeRecherche").equals("Instances d'un Concept")) {
            System.out.println(dicoDonnees.get("concept"));
            boolean afficherAttributs = dicoDonnees.get("afficherAttributs").equals("true");
            Graphe grapheRecherche = grapheLogique.rechercheInstancesDeConcept(dicoDonnees.get("concept"),
                    afficherAttributs);
            grapheRecherche.updateVisualGraph(grapheVisuel);
        } else if (dicoDonnees.get("typeRecherche").equals("Afficher le graphe principal")) {
            grapheLogique.updateVisualGraph(grapheVisuel);
        } else if (dicoDonnees.get("typeRecherche").equals("Noeuds qui ont un attribut")) {
            // TODO
        }
    }

}
