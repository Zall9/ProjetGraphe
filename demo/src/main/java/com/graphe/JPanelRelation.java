package com.graphe;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.util.ArrayList;
//import org.graphstream.graph.Graph;
public class JPanelRelation extends JPanel{
    //private Graph graphVisuel;
    private Graphe grapheLogique;
    private JComboBox<String> comboNoeudGauche;
    private JComboBox<String> comboNoeudDroit;
    private JComboBox<String> comboRelation;
    private JPanel panelRelation;
    JPanelRelation(Graphe grapheLogique){
        super();
        FlowLayout layout = new FlowLayout();
        JComboBox<String> comboNoeudGauche=new JComboBox<String>();
        JComboBox<String> comboNoeudDroit=new JComboBox<String>();
        JComboBox<String> comboRelation=new JComboBox<String>();
        System.out.println("prout0");
        setLayout(layout);
        System.out.println("prout1");
        this.grapheLogique = grapheLogique;
        System.out.println("prout");
        for (Noeud n : (ArrayList<Noeud>) grapheLogique.getGraphe()) {
            
            comboNoeudGauche.addItem(n.toString());
            comboNoeudDroit.addItem(n.toString());
        }
        for (TypeRelation rel : TypeRelation.values()) {
            comboRelation.addItem(rel.toString());
        }
        AutoCompleteDecorator.decorate(comboNoeudGauche);
        AutoCompleteDecorator.decorate(comboNoeudDroit);
        AutoCompleteDecorator.decorate(comboRelation);
        
        JLabel noeud1 = new JLabel("Noeud : ");
        JLabel relationLabel = new JLabel("Relation: ");
        JLabel noeud2 = new JLabel("Noeud : ");
        this.add(noeud1);
        this.add(comboNoeudGauche);
        this.add(relationLabel);
        this.add(comboRelation);
        this.add(noeud2);
        this.add(comboNoeudDroit);
    }
}


