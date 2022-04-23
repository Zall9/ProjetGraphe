package com.graphe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.graphstream.graph.Graph;

public class BoutonCreerRelationAL implements ActionListener{

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
        
    }
    
}
