package com.graphe;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
//import org.graphstream.graph.Graph;
public class JPanelRelation extends JPanel{
    //private Graph graphVisuel;
    private Graphe grapheLogique;
    private JComboBox<String> comboChoseRelation;

    JPanelRelation(Graphe graphe){
        this.grapheLogique = graphe;
    }

}
