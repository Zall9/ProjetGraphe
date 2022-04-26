package com.graphe;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import org.graphstream.graph.Graph;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class JPanelParcours extends JPanel {
    private String typeRecherche[];
    private JComboBox<String> comboSelection;
    private JButton boutonRecherche;
    private JComboBox<Noeud> comboRecherche;
    private JPanel pan;
    private Map<String, Object> dicoDonnees;
    private JComboBox<Relation> comboRelation;
    private Map<String, ArrayList<Relation>> listeRelations;

    JPanelParcours(Graphe grapheLogique, Graph grapheVisuel) {
        super();
        pan = new JPanel();
        listeRelations = new HashMap<String, ArrayList<Relation>>();
        comboSelection = new JComboBox<String>();
        dicoDonnees = new HashMap<String, Object>();
        FlowLayout layout = new FlowLayout();
        pan.setLayout(layout);
        typeRecherche = new String[] { "Instances d'un Concept", "Afficher le graphe principal",
                "Noeuds selon relation", "Parcours selon une relation" };
        JLabel label = new JLabel("Parcours : ");
        for (String str : typeRecherche) {
            comboSelection.addItem(str);
        }
        boutonRecherche = new JButton("Chercher !");
        add(label);
        add(comboSelection);

        // On est par défaut sur recherche des instances d'un concept
        comboRecherche = new JComboBox<Noeud>();
        // listener sur le comboBox de la selection du concept qui met a jour le dico de
        // données
        comboRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dicoDonnees.put("concept", comboRecherche.getSelectedItem().toString());
            }
        });
        dicoDonnees.put("typeRecherche", typeRecherche[0]);
        AutoCompleteDecorator.decorate(comboRecherche);
        for (Noeud n : grapheLogique.getGraphe()) {
            if (n.getTypeNoeud().equals("Concept")) {
                comboRecherche.addItem(n);
            }
        }
        JCheckBox cb = new JCheckBox("Afficher attributs");
        cb.setSelected(true);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dicoDonnees.put("afficherAttributs", cb.isSelected() + "");
            }
        });
        dicoDonnees.put("afficherAttributs", "true");

        pan.add(comboRecherche);
        pan.add(cb);

        // Ajout du listener sur la selection du type de recherche
        comboSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboSelection.getSelectedItem());
                pan.removeAll();

                if (comboSelection.getSelectedItem().equals(typeRecherche[0])) {
                    comboRecherche = new JComboBox<Noeud>();
                    // listener sur le comboBox de la selection du concept qui met a jour le dico de
                    // données
                    comboRecherche.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            dicoDonnees.put("concept", comboRecherche.getSelectedItem().toString());
                        }
                    });

                    dicoDonnees.put("typeRecherche", typeRecherche[0]);
                    AutoCompleteDecorator.decorate(comboRecherche);
                    for (Noeud n : grapheLogique.getGraphe()) {
                        if (n.getTypeNoeud().equals("Concept")) {
                            comboRecherche.addItem(n);
                        }
                    }

                    JCheckBox cb = new JCheckBox("Afficher attributs");
                    cb.setSelected(true);
                    dicoDonnees.put("afficherAttributs", cb.isSelected() + "");
                    cb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            dicoDonnees.put("afficherAttributs", cb.isSelected() + "");
                        }
                    });
                    pan.add(comboRecherche);
                    pan.add(cb);
                } else if (comboSelection.getSelectedItem().equals(typeRecherche[1])) {
                    dicoDonnees.put("typeRecherche", typeRecherche[1]);

                } else if (comboSelection.getSelectedItem().equals(typeRecherche[2])
                        || comboSelection.getSelectedItem().equals(typeRecherche[3])) {
                    listeRelations.clear();
                    if (comboSelection.getSelectedItem().equals(typeRecherche[2])) {
                        dicoDonnees.put("typeRecherche", typeRecherche[2]);
                    } else {
                        dicoDonnees.put("typeRecherche", typeRecherche[3]);
                    }
                    comboRecherche = new JComboBox<Noeud>();
                    AutoCompleteDecorator.decorate(comboRecherche);
                    for (Noeud n : grapheLogique.getGraphe()) {
                        if (!n.getTypeNoeud().equals("Attribut")) {
                            comboRecherche.addItem(n);
                        }
                    }
                    dicoDonnees.put("noeudRecherche", comboRecherche.getSelectedItem());
                    comboRelation = new JComboBox<Relation>();
                    AutoCompleteDecorator.decorate(comboRelation);
                    Noeud nSelectione = (Noeud) comboRecherche.getSelectedItem();
                    int cpt = 0;
                    for (Relation r : nSelectione.getRelations()) {
                        if (cpt == 0) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(r);
                            dicoDonnees.put("relation", arrayList);
                        }
                        if (!listeRelations.containsKey(r.toString())) {
                            comboRelation.addItem(r);
                            listeRelations.put(r.toString(), new ArrayList<Relation>());
                        }
                        listeRelations.get(r.toString()).add(r);
                        cpt++;
                    }
                    comboRelation.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            dicoDonnees.put("relation", listeRelations.get(comboRelation.getSelectedItem().toString()));
                        }
                    });
                    comboRecherche.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            pan.remove(comboRelation);
                            dicoDonnees.put("noeudRecherche", comboRecherche.getSelectedItem());
                            comboRelation = new JComboBox<Relation>();
                            AutoCompleteDecorator.decorate(comboRelation);
                            Noeud nSelectione = (Noeud) comboRecherche.getSelectedItem();
                            int cpt = 0;
                            for (Relation r : nSelectione.getRelations()) {
                                if (cpt == 0) {
                                    ArrayList<Relation> arrayList = new ArrayList<Relation>();
                                    arrayList.add(r);
                                    dicoDonnees.put("relation", arrayList);
                                }
                                if (!listeRelations.containsKey(r.toString())) {
                                    comboRelation.addItem(r);
                                    listeRelations.put(r.toString(), new ArrayList<Relation>());
                                }
                                listeRelations.get(r.toString()).add(r);
                                cpt++;
                            }
                            comboRelation.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    dicoDonnees.put("relation",
                                            listeRelations.get(comboRelation.getSelectedItem().toString()));
                                }
                            });

                            pan.add(comboRelation);
                            pan.revalidate();
                            pan.repaint();
                            pan.setMaximumSize(pan.getPreferredSize());
                            ;
                        }
                    });
                    pan.add(comboRecherche);
                    pan.add(comboRelation);
                }
                pan.revalidate();
                pan.repaint();
                pan.setMaximumSize(pan.getPreferredSize());
            }
        });
        boutonRecherche.addActionListener(new RechercheActionListener(grapheLogique, grapheVisuel, dicoDonnees));
        add(pan);
        add(boutonRecherche);
    }
}