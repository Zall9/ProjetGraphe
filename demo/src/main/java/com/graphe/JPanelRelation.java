package com.graphe;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import org.graphstream.graph.Graph;
public class JPanelRelation extends JPanel{
    //private Graph graphVisuel;
    private Graphe grapheLogique;
    private JComboBox<String> comboNoeudGauche;
    private JComboBox<String> comboNoeudDroit;
    private JComboBox<String> comboRelation;
    private JPanel panelRelation;
    private JButton button;
    private JButton buttonRefresh;
    JPanelRelation(Graphe grapheLogique, JButton button){
        super();
        this.button = button;
        FlowLayout layout = new FlowLayout();
        JComboBox<Noeud> comboNoeudGauche=new JComboBox<Noeud>();
        JComboBox<Noeud> comboNoeudDroit=new JComboBox<Noeud>();
        JComboBox<TypeRelation> comboRelation=new JComboBox<TypeRelation>();
        buttonRefresh= new JButton("Refresh");
        setLayout(layout);
        
        this.grapheLogique = grapheLogique;
        
        for (Noeud n : (ArrayList<Noeud>) grapheLogique.getGraphe()) {
            
            comboNoeudGauche.addItem(n);
            comboNoeudDroit.addItem(n);
        }
        comboNoeudGauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboNoeudGauche.getSelectedItem().toString());
            }
        });
        comboNoeudDroit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboNoeudGauche.getSelectedItem().toString());
            }
        });
        comboRelation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboNoeudGauche.getSelectedItem().toString());
            }
        });

        for (TypeRelation rel : TypeRelation.values()) {
            comboRelation.addItem(rel);
        }

        AutoCompleteDecorator.decorate(comboNoeudGauche);
        AutoCompleteDecorator.decorate(comboNoeudDroit);
        AutoCompleteDecorator.decorate(comboRelation);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRelation((Noeud)comboNoeudGauche.getSelectedItem(), (Noeud)comboNoeudDroit.getSelectedItem(),(TypeRelation) comboRelation.getSelectedItem());
                grapheLogique.convertToVisualGraph();
            }
        });
        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                for (Noeud n : (ArrayList<Noeud>) grapheLogique.getGraphe()) {
                    comboNoeudGauche.removeItem(n);
                    comboNoeudDroit.removeItem(n);
                }
                for (Noeud n : (ArrayList<Noeud>) grapheLogique.getGraphe()) {
                    comboNoeudGauche.addItem(n);
                    comboNoeudDroit.addItem(n);
                }
            }
        });

        JLabel noeud1 = new JLabel("Noeud : ");
        JLabel relationLabel = new JLabel("Relation: ");
        JLabel noeud2 = new JLabel("Noeud : ");
        this.add(noeud1);
        this.add(comboNoeudGauche);
        this.add(relationLabel);
        this.add(comboRelation);
        this.add(noeud2);
        this.add(comboNoeudDroit);
        this.add(button);
        this.add(buttonRefresh);
    }
    /**
     * crée une relation entre deux nœuds
     * 
     * @param n1 le premier nœud
     * @param n2 le nœud qui sera la cible de la relation
     * @param typeRelation le type de la relation
     */
    
    private void createRelation(Noeud n1, Noeud n2, TypeRelation typeRelation){
        Relation r = new Relation(typeRelation, "type", n1, n2);
        n1.relieNoeuds(n2,r);
        
    }
}


