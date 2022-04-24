package com.graphe;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JButton;
import org.graphstream.graph.Graph;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



class JPanelParcours extends JPanel{
    private String typeRecherche[];
    private JComboBox<String> comboSelection;
    private JButton bouton;
    private JComboBox<Noeud> comboRecherche;
    private Graphe g;
    private JPanel pan;
    JPanelParcours(Graphe g) {
        super();
        pan= new JPanel();
        this.g=g;
        comboSelection = new JComboBox<String>();
        FlowLayout layout = new FlowLayout();
        pan.setLayout(layout);
        typeRecherche = new String[]{"Instances d'un Concept","Noeuds qui ont un attribut"};
        JLabel label = new JLabel("Parcours : ");
        AutoCompleteDecorator.decorate(comboSelection);
        for (String str: typeRecherche) {
            comboSelection.addItem(str);
        }
        //comboRecherche = new JComboBox<Noeud>();
        bouton = new JButton("Chercher !");
        pan.add(label);
        pan.add(comboSelection);
        comboSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pan.getComponent(3)!=null){
                    pan.remove(3);}
                if (comboSelection.getSelectedItem().equals(typeRecherche[0])) {
                    comboRecherche = new JComboBox<Noeud>();
                    AutoCompleteDecorator.decorate(comboRecherche);
                    for(Noeud n : g.getGraphe()){
                        System.out.println("Type de n: "+n.getTypeNoeud());
                        System.out.println("n.getTypeNoeud().equals(Concept)"+n.getTypeNoeud().equals("Concept"));
                        if(n.getTypeNoeud().equals("Concept")){
                            comboRecherche.addItem(n);
                        }
                    }
                }
                else if (comboSelection.getSelectedItem().equals("Noeuds qui ont un attribut")) {
                    comboRecherche = new JComboBox<Noeud>();
                    AutoCompleteDecorator.decorate(comboRecherche);
                    for(Noeud n : g.getGraphe()){
                        if(n.getTypeNoeud().equals("Attribut")){
                            comboRecherche.addItem(n);
                        }
                    }
                }
                pan.add(comboRecherche);
                pan.repaint();
            }
        });   
        add(pan);
    }
}