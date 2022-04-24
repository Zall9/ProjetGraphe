package com.graphe;

import javax.swing.JPanel;

public class JPanelAvecTypeNoeud extends JPanel {
    private String typeNoeud;

    /**
     * 
     * 
     * @return The type of the node.
     */
    public String getTypeNoeud() {
        return this.typeNoeud;
    }

    /**
     * This function sets the type of the node
     * 
     * @param typeNoeud The type of node.
     */
    public void setTypeNoeud(String typeNoeud) {
        this.typeNoeud = typeNoeud;
    }

}
