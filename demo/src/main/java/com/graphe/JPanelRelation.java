package com.graphe;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.graphstream.graph.Graph;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JPanelRelation extends JPanel {
    // private Graph graphVisuel;
    private Graphe grapheLogique;
    private Graph grapheVisuel;
    private JComboBox<Noeud> comboNoeudGauche;
    private JComboBox<Noeud> comboNoeudDroit;
    private JComboBox<TypeRelation> comboRelation;
    private JButton boutonCreerRelation;
    private HintTextField textFieldNomRelation;

    JPanelRelation(Graphe grapheLogique, Graph grapheVisuel, JButton boutonCreerRelation) {
        super();
        this.boutonCreerRelation = boutonCreerRelation;
        FlowLayout layout = new FlowLayout();
        this.textFieldNomRelation = new HintTextField("Nom de la relation");
        comboNoeudGauche = new JComboBox<Noeud>();
        comboNoeudDroit = new JComboBox<Noeud>();
        comboRelation = new JComboBox<TypeRelation>();
        setLayout(layout);
        this.grapheVisuel = grapheVisuel;
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

        boutonCreerRelation.addActionListener(new BoutonCreerRelationAL(this, grapheLogique, grapheVisuel));

        JLabel noeud1 = new JLabel("Noeud : ");
        JLabel relationLabel = new JLabel("Relation: ");
        JLabel noeud2 = new JLabel("Noeud : ");

        this.add(noeud1);
        this.add(comboNoeudGauche);
        this.add(relationLabel);
        this.add(comboRelation);
        this.add(textFieldNomRelation);
        this.add(noeud2);
        this.add(comboNoeudDroit);
        this.add(boutonCreerRelation);

    }

    /**
     * crée une relation logique entre deux nœuds
     * 
     * @param n1           le premier nœud
     * @param n2           le nœud qui sera la cible de la relation
     * @param typeRelation le type de la relation
     */

    public Relation createRelationLogique(Noeud n1, Noeud n2, TypeRelation typeRelation, String nomRelation) {
        Relation r = new Relation(typeRelation, nomRelation, n1, n2);
        n1.relieNoeuds(n2, r);
        return r;
    }

    public void createRelationVisuel(Noeud n1, Noeud n2, Relation r) {
        System.out.println("relie:" + n2);
        grapheVisuel.addEdge(r.getId(), n1.getId(), n2.getId(), true);
        grapheVisuel.getEdge(r.getId()).setAttribute("ui.label", r.getRelLabel());
    }

    /**
     * Cette fonction renvoie l'objet comboNoeudGauche
     * 
     * @return L'objet comboNoeudGauche.
     */
    public JComboBox<Noeud> getComboNoeudGauche() {
        return this.comboNoeudGauche;
    }

    /**
     * * Cette fonction renvoie l'objet comboNoeudDroit
     * 
     * @return La zone de liste déroulante du nœud droit.
     */
    public JComboBox<Noeud> getComboNoeudDroit() {
        return this.comboNoeudDroit;
    }

    /**
     * * Cette fonction renvoie l'objet comboRelation
     * 
     * @return L'objet comboRelation.
     */
    public JComboBox<TypeRelation> getComboRelation() {
        return this.comboRelation;
    }

    public HintTextField getTextFieldNomRelation() {
        return this.textFieldNomRelation;
    }
}
