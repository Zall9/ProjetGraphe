
package com.graphe;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphe {
    private List<Noeud> noeuds = new ArrayList<Noeud>();

    private void afficheGraphe() {
        for (Noeud n : noeuds) {
            System.out.println(n + " | id : " + n.getId());
            n.afficheRelations();
            System.out.println("-----------------");

        }
    }

    private void saisirInfo() {
    }

    private void sauvegarderGraphe() {
    }

    private void chargerGraphe() {
    }

    /**
     * It returns the list of nodes of the graph
     * 
     * @return The list of nodes.
     */
    private List<Noeud> getGraphe() {
        return this.noeuds;
    }

    /**
     * It returns a string
     * 
     * @param p1 the first node
     * @param p2 the destination node
     */
    private String chemin(Noeud p1, Noeud p2) {
        return "";
    }

    /**
     * It adds a node to the list of nodes
     * 
     * @param n the node to add
     */
    public void ajouterNoeud(Noeud n) {
        noeuds.add(n);
    }

    /**
     * It converts the graph from the program to a graph that can be displayed by
     * the library
     * 
     * @return A graph object
     */
    private Graph convertToVisualGraph() throws ElementNotFoundException {
        List<Relation> relsCourant;
        int cpt;
        Graph graph = new SingleGraph("Graphe");
        for (Noeud n : noeuds) {
            graph.addNode(n.getId());
            System.out.println("noeud ajouté a graph " + n);
            graph.getNode(n.getId()).setAttribute("ui.label", n.toString());
        }
        for (Noeud n : noeuds) {
            System.out.println("noeud courant: " + n);
            relsCourant = n.getRelations();
            cpt = 0;
            for (Noeud n2 : n.getNoeudsRelie()) {

                System.out.println("relie:" + n2);
                graph.addEdge(relsCourant.get(cpt).getId(), n.getId(), n2.getId(), true);
                graph.getEdge(relsCourant.get(cpt).getId()).setAttribute("ui.label",
                        relsCourant.get(cpt).getRelLabel());
                cpt++;

            }
        }
        return graph;
    }

    public void comboBoxitemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedItem = (String) e.getItem();
        }
    }

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        JFrame maFenetre = new JFrame("Graphe");
        JPanel pa = new JPanel();
        String s1[] = { "Concept", "Instance", "Attribut" };
        JComboBox<String> combo = new JComboBox<String>(s1);
        String mavar = "";
        Graphe g = new Graphe();

        // ask user to input
        ConceptNoeud cN1 = new ConceptNoeud(TypeConcept.foaf, "Person");
        InstanceNoeud instanceAlice = new InstanceNoeud("Alice");
        AttributNoeud attributAge = new AttributNoeud("24");
        AttributNoeud attributNom = new AttributNoeud("Alice");
        // ConceptNoeud cN2 = new ConceptNoeud(TypeConcept.ex, "Pronom");
        AttributNoeud attributPronom = new AttributNoeud("Iel");
        
        // todo RECUPERER LA RELATION ET FAIRE RELATION.TOSTRING
        Relation rInstanceN1 = new Relation(TypeRelation.rdf, "type", instanceAlice, cN1);
        Relation rAttributN2 = new Relation(TypeRelation.foaf, "age", instanceAlice, attributAge);
        Relation rAttributN3 = new Relation(TypeRelation.foaf, "name", attributNom, instanceAlice);
        Relation rAttributN4 = new Relation(TypeRelation.foaf, "pronom", instanceAlice, attributPronom);
        // ajouter les relations au graphe
        instanceAlice.relieNoeuds(cN1, rInstanceN1);
        instanceAlice.relieNoeuds(attributAge, rAttributN2);
        instanceAlice.relieNoeuds(attributNom, rAttributN3);
        instanceAlice.relieNoeuds(attributPronom, rAttributN4);
        // ajouter les noeuds au graphe
        g.ajouterNoeud(cN1);
        g.ajouterNoeud(instanceAlice);
        g.ajouterNoeud(attributAge);
        g.ajouterNoeud(attributNom);
        g.ajouterNoeud(attributPronom);
        g.afficheGraphe();
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = g.convertToVisualGraph();

        java.nio.file.Path filePath = java.nio.file.Paths.get("demo/src/main/java/com/graphe/vue", "style.css");

        try {
            graph.setAttribute("ui.stylesheet", Files.readString(filePath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        DefaultView view = (DefaultView) viewer.addDefaultView(false); // false indicates "no JFrame".
        
        // Création des boutons
        JLabel labelAjoutNoeud = new JLabel("Ajouter un noeud");
        JLabel labelAjoutRelation = new JLabel("Ajouter une relation");
        JPanel panel = new JPanel();
        
        // Choix création noeud
        JPanel panelChoixCreerNoeudConteneur = new JPanel();
        // Au départ on est sur concept
        JPanelAvecTypeNoeud panelChoixCreerNoeudCourant = new JPanelConcept();
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        JButton boutonCreerNoeud = new JButton("Créer un noeud");
        boutonCreerNoeud.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton bouton1 = new JButton("Afficher");
        bouton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton bouton2 = new JButton("Sauvegarder");
        bouton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton bouton3 = new JButton("Charger");
        bouton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton bouton4 = new JButton("Quitter");
        bouton4.setAlignmentX(Component.CENTER_ALIGNMENT);

        System.out.println(combo.getSelectedItem() + "");
        // listener sur la box
        combo.addActionListener(new ChoixTypeConceptActionListener(combo, panelChoixCreerNoeudConteneur,
                panelChoixCreerNoeudCourant, boutonCreerNoeud, graph, g));

        // Création des réactions pour les bouttons
        boutonCreerNoeud.addActionListener(new CreerNoeudActionListener(panelChoixCreerNoeudCourant, g, graph));

        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ici !");
            }
        });
        bouton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ici !");
            }
        });
        bouton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ici !");
            }
        });
        bouton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ajouts au panelChoixCreerNoeud
        panelChoixCreerNoeudConteneur.add(panelChoixCreerNoeudCourant);

        // Ajout des boutons au panel principal
        combo.setMaximumSize(combo.getPreferredSize());
        panel.add(labelAjoutNoeud);
        panel.add(combo);
        panelChoixCreerNoeudConteneur.setMaximumSize(panelChoixCreerNoeudConteneur.getPreferredSize());
        panel.add(panelChoixCreerNoeudConteneur);
        panel.add(boutonCreerNoeud);
        panel.add(labelAjoutRelation);
        panel.add(bouton1);
        panel.add(bouton2);
        panel.add(bouton3);
        panel.add(bouton4);
        // Création de la fenetre et ajout des composants:
        maFenetre.setSize(800, 600);
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFenetre.setLocationRelativeTo(null);
        maFenetre.add(panel, BorderLayout.EAST);
        maFenetre.add(view, BorderLayout.CENTER);
        maFenetre.setVisible(true);

    }

}
