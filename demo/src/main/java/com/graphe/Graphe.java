
package com.graphe;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.nio.file.Path;
import java.nio.file.Paths;

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

    /**
     * It returns the list of nodes of the graph
     * 
     * @return The list of nodes.
     */
    public List<Noeud> getGraphe() {
        return this.noeuds;
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
    Graph convertToVisualGraph() throws ElementNotFoundException {
        List<Relation> relsCourant;
        int cpt;
        Graph graph = new SingleGraph("Graphe");
        Path filePath = Paths.get("demo/src/main/java/com/graphe/vue", "style.css");
        try {
            List<String> contentArray = Files.readAllLines(filePath);
            String content = String.join("\n", contentArray);
            graph.setAttribute("ui.stylesheet", content);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (Noeud n : noeuds) {
            graph.addNode(n.getId());
            System.out.println("noeud ajouté a graph " + n);
            graph.getNode(n.getId()).setAttribute("ui.label", n.toString());
            graph.getNode(n.getId()).setAttribute("ui.class", n.getTypeNoeud());
        }
        for (Noeud n : noeuds) {
            System.out.println("noeud courant: " + n);
            relsCourant = n.getRelations();
            cpt = 0;
            for (Noeud n2 : n.getNoeudsRelie()) {
                if (estDansGraphe(n2)) {
                    System.out.println("relie:" + n2);
                    graph.addEdge(relsCourant.get(cpt).getId(), n.getId(), n2.getId(), true);
                    graph.getEdge(relsCourant.get(cpt).getId()).setAttribute("ui.label",
                            relsCourant.get(cpt).getRelLabel());
                    cpt++;
                }

            }
        }
        return graph;
    }

    void updateVisualGraph(Graph graph) {
        List<Relation> relsCourant;
        int cpt;
        graph.clear();
        Path filePath = Paths.get("demo/src/main/java/com/graphe/vue", "style.css");
        try {
            List<String> contentArray = Files.readAllLines(filePath);
            String content = String.join("\n", contentArray);
            graph.setAttribute("ui.stylesheet", content);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (Noeud n : noeuds) {
            graph.addNode(n.getId());
            System.out.println("noeud ajouté a graph " + n);
            graph.getNode(n.getId()).setAttribute("ui.label", n.toString());
            graph.getNode(n.getId()).setAttribute("ui.class", n.getTypeNoeud());
        }
        for (Noeud n : noeuds) {
            System.out.println("noeud courant: " + n);
            relsCourant = n.getRelations();
            cpt = 0;
            for (Noeud n2 : n.getNoeudsRelie()) {
                if (estDansGraphe(n2)) {
                    System.out.println("relie:" + n2);
                    graph.addEdge(relsCourant.get(cpt).getId(), n.getId(), n2.getId(), true);
                    graph.getEdge(relsCourant.get(cpt).getId()).setAttribute("ui.label",
                            relsCourant.get(cpt).getRelLabel());
                }
                cpt++;

            }
        }
    }

    /**
     * It takes a graph and a relation as parameters, clears the graph, adds the
     * nodes and edges to the
     * graph, and then displays the graph
     * 
     * @param graph the graph to be displayed
     * @param r     the relation that we want to display
     */
    void updateVisualGraph(Graph graph, ArrayList<Relation> r) {
        List<Relation> relsCourant;
        int cpt;
        graph.clear();
        Path filePath = Paths.get("demo/src/main/java/com/graphe/vue", "style.css");
        try {
            List<String> contentArray = Files.readAllLines(filePath);
            String content = String.join("\n", contentArray);
            graph.setAttribute("ui.stylesheet", content);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (Noeud n : noeuds) {
            System.out.println("NOEUD ALED:" + n);
            graph.addNode(n.getId());
            graph.getNode(n.getId()).setAttribute("ui.label", n.toString());
            graph.getNode(n.getId()).setAttribute("ui.class", n.getTypeNoeud());
        }
        for (Noeud n : noeuds) {
            System.out.println("noeud courant: " + n);
            relsCourant = n.getRelations();
            cpt = 0;
            for (Noeud n2 : n.getNoeudsRelie()) {
                if (estDansGraphe(n2)) {
                    if (n.getRelations().get(cpt).toString().equals(r.get(0).toString())) {
                        System.out.println("relie:" + n2);
                        graph.addEdge(relsCourant.get(cpt).getId(), n.getId(), n2.getId(), true);
                        graph.getEdge(relsCourant.get(cpt).getId()).setAttribute("ui.label",
                                relsCourant.get(cpt).getRelLabel());
                    }

                }
                cpt++;
            }
        }
    }

    public void comboBoxitemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedItem = (String) e.getItem();
        }
    }

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        JFrame maFenetre = new JFrame("Graphe");
        String s1[] = { "Concept", "Instance", "Attribut" };
        JComboBox<String> combo = new JComboBox<String>(s1);
        Graphe g = new Graphe();

        // -------------- Noeuds --------------
        ConceptNoeud cN1 = new ConceptNoeud(TypeConcept.ex, "Film");
        ConceptNoeud cN3 = new ConceptNoeud(TypeConcept.ex, "Jeu-Video");
        ConceptNoeud cN2 = new ConceptNoeud(TypeConcept.foaf, "Acteur");
        InstanceNoeud iN1 = new InstanceNoeud("Matrix");
        InstanceNoeud iN2 = new InstanceNoeud("Matrix2");
        InstanceNoeud iN3 = new InstanceNoeud("Matrix3");
        InstanceNoeud iN4 = new InstanceNoeud("The Matrix");

        InstanceNoeud keanu = new InstanceNoeud("Keanu Reeves");
        InstanceNoeud fishburne = new InstanceNoeud("Laurence Fishburne");
        AttributNoeud attributAgeKeanu = new AttributNoeud("57");
        AttributNoeud attributRoleKeanu = new AttributNoeud("Neo");
        AttributNoeud attributAgeBurnes = new AttributNoeud("60");
        AttributNoeud attributRoleBurnes = new AttributNoeud("Morpheus");

        AttributNoeud attributDateSortieMatrix = new AttributNoeud("1999");
        AttributNoeud attributTitreMatrix = new AttributNoeud("\"Matrix\"");
        AttributNoeud attributGenreMatrix = new AttributNoeud("Science-Fiction");
        AttributNoeud attribut2GenreMatrix = new AttributNoeud("Action");

        AttributNoeud attributDateSortieMatrix2 = new AttributNoeud("2003");
        AttributNoeud attributTitreMatrix2 = new AttributNoeud("\"Matrix: Reloaded\"");
        AttributNoeud attributGenreMatrix2 = new AttributNoeud("Science-Fiction");
        AttributNoeud attribut2GenreMatrix2 = new AttributNoeud("Action");

        AttributNoeud attributDateSortieMatrix3 = new AttributNoeud("2003");
        AttributNoeud attributTitreMatrix3 = new AttributNoeud("\"Matrix: Revolutions\"");

        AttributNoeud attributDateSortieMatrixPS2 = new AttributNoeud("2001");
        AttributNoeud attributTitreMatrixPS2 = new AttributNoeud("\"The Matrix\"");
        AttributNoeud attributGenreMatrixPS2 = new AttributNoeud("Science-Fiction");
        AttributNoeud attribut2GenreMatrixPS2 = new AttributNoeud("Action");

        // -------------- Relations --------------

        Relation relNomKeanu = new Relation(TypeRelation.foaf, "role", keanu, attributRoleKeanu);
        Relation relActeurKeanu = new Relation(TypeRelation.rdf, "type", keanu, cN2);
        Relation relKeanuMatrix1 = new Relation(TypeRelation.dc, "Modele", keanu, iN1);
        Relation relKeanuMatrix2 = new Relation(TypeRelation.dc, "Modele", keanu, iN2);
        Relation relAgeKeanu = new Relation(TypeRelation.foaf, "age", keanu, attributAgeKeanu);

        Relation relNomBurnes = new Relation(TypeRelation.foaf, "role", fishburne, attributRoleBurnes);
        Relation relActeurBurnes = new Relation(TypeRelation.rdf, "type", fishburne, cN2);
        Relation relBurnesMatrix1 = new Relation(TypeRelation.dc, "acteur", fishburne, iN1);
        Relation relBurnesMatrix2 = new Relation(TypeRelation.dc, "acteur", fishburne, iN2);
        Relation relAgeBurnes = new Relation(TypeRelation.foaf, "age", fishburne, attributAgeBurnes);

        Relation matrix1Date = new Relation(TypeRelation.dc, "date", iN1, attributDateSortieMatrix);
        Relation matrix1Titre = new Relation(TypeRelation.dc, "titre", iN1, attributTitreMatrix);
        Relation matrix1Genre = new Relation(TypeRelation.dc, "genre", iN1, attributGenreMatrix);
        Relation matrix1Genre2 = new Relation(TypeRelation.dc, "genre", iN1, attribut2GenreMatrix);
        Relation relSuiteMatrix1 = new Relation(TypeRelation.rdfs, "seeAlso", iN1, iN2);
        Relation matrixFilm = new Relation(TypeRelation.rdf, "type", iN1, cN1);
        Relation matrixActeur1 = new Relation(TypeRelation.dc, "avec", iN1, keanu);
        Relation matrixActeur2 = new Relation(TypeRelation.dc, "avec", iN1, fishburne);

        Relation relInstanceMatrix2 = new Relation(TypeRelation.rdf, "type", iN2, cN1);
        Relation relSuiteMatrix2 = new Relation(TypeRelation.rdfs, "seeAlso", iN2, iN3);
        Relation relAttributMatrix2titre = new Relation(TypeRelation.dc, "titre", iN2, attributTitreMatrix2);
        Relation relAttributMatrix2date = new Relation(TypeRelation.dc, "date", iN2, attributDateSortieMatrix2);
        Relation relAttributMatrix2genre = new Relation(TypeRelation.dc, "genre", iN2, attributGenreMatrix2);
        Relation relAttributMatrix2genre2 = new Relation(TypeRelation.dc, "genre", iN2, attribut2GenreMatrix2);
        Relation relMatrix2Acteur1 = new Relation(TypeRelation.dc, "avec", iN2, fishburne);
        Relation relMatrix2Acteur2 = new Relation(TypeRelation.dc, "avec", iN2, keanu);
        Relation relSuiteMatrix2PS2 = new Relation(TypeRelation.rdfs, "seeAlso", iN2, iN4);

        Relation relInstanceMatrix3 = new Relation(TypeRelation.rdf, "type", iN3, cN1);
        Relation relAttributMatrix3date = new Relation(TypeRelation.foaf, "dateSortie", iN3, attributDateSortieMatrix3);
        Relation relAttributMatrix3titre = new Relation(TypeRelation.foaf, "titre", iN3, attributTitreMatrix3);

        Relation relMatrixPS2 = new Relation(TypeRelation.rdf, "type", iN4, cN3);
        Relation relMatrixPS2Date = new Relation(TypeRelation.dc, "date", iN4, attributDateSortieMatrixPS2);
        Relation relMatrixPS2Titre = new Relation(TypeRelation.dc, "titre", iN4, attributTitreMatrixPS2);
        Relation relMatrixPS2Genre = new Relation(TypeRelation.dc, "genre", iN4, attributGenreMatrixPS2);
        Relation relMatrixPS2Genre2 = new Relation(TypeRelation.dc, "genre", iN4, attribut2GenreMatrixPS2);
        Relation relMatrixPS2Acteur1 = new Relation(TypeRelation.dc, "avec", iN4, fishburne);
        Relation relMatrixPS2Acteur2 = new Relation(TypeRelation.dc, "avec", iN4, keanu);
        Relation relMatrixPS2suite = new Relation(TypeRelation.dc, "seeAlso", iN4, iN1);

        // ajouter les relations au graphe
        keanu.relieNoeuds(iN1, relKeanuMatrix1);
        keanu.relieNoeuds(iN2, relKeanuMatrix2);
        keanu.relieNoeuds(cN2, relActeurKeanu);
        keanu.relieNoeuds(attributRoleKeanu, relNomKeanu);
        keanu.relieNoeuds(attributAgeKeanu, relAgeKeanu);

        iN1.relieNoeuds(attributDateSortieMatrix, matrix1Date);
        iN1.relieNoeuds(attributTitreMatrix, matrix1Titre);
        iN1.relieNoeuds(attributGenreMatrix, matrix1Genre);
        iN1.relieNoeuds(attribut2GenreMatrix, matrix1Genre2);
        iN1.relieNoeuds(cN1, matrixFilm);
        iN1.relieNoeuds(iN2, relSuiteMatrix1);
        iN1.relieNoeuds(keanu, matrixActeur1);
        iN1.relieNoeuds(fishburne, matrixActeur2);

        iN2.relieNoeuds(cN1, relInstanceMatrix2);
        iN2.relieNoeuds(attributTitreMatrix2, relAttributMatrix2titre);
        iN2.relieNoeuds(attributDateSortieMatrix2, relAttributMatrix2date);
        iN2.relieNoeuds(iN3, relSuiteMatrix2);
        iN2.relieNoeuds(attributGenreMatrix2, relAttributMatrix2genre);
        iN2.relieNoeuds(attribut2GenreMatrix2, relAttributMatrix2genre2);
        iN2.relieNoeuds(fishburne, relMatrix2Acteur1);
        iN2.relieNoeuds(keanu, relMatrix2Acteur2);
        iN2.relieNoeuds(iN4, relSuiteMatrix2PS2);

        iN3.relieNoeuds(cN1, relInstanceMatrix3);
        iN3.relieNoeuds(attributDateSortieMatrix3, relAttributMatrix3date);
        iN3.relieNoeuds(attributTitreMatrix3, relAttributMatrix3titre);

        iN4.relieNoeuds(cN3, relMatrixPS2);
        iN4.relieNoeuds(attributDateSortieMatrixPS2, relMatrixPS2Date);
        iN4.relieNoeuds(attributTitreMatrixPS2, relMatrixPS2Titre);
        iN4.relieNoeuds(attributGenreMatrixPS2, relMatrixPS2Genre);
        iN4.relieNoeuds(attribut2GenreMatrixPS2, relMatrixPS2Genre2);
        iN4.relieNoeuds(fishburne, relMatrixPS2Acteur1);
        iN4.relieNoeuds(keanu, relMatrixPS2Acteur2);
        iN4.relieNoeuds(iN1, relMatrixPS2suite);

        fishburne.relieNoeuds(cN2, relActeurBurnes);
        fishburne.relieNoeuds(iN1, relBurnesMatrix1);
        fishburne.relieNoeuds(iN2, relBurnesMatrix2);
        fishburne.relieNoeuds(attributRoleBurnes, relNomBurnes);
        fishburne.relieNoeuds(attributAgeBurnes, relAgeBurnes);

        // ajouter les noeuds au graphe
        g.ajouterNoeud(cN1);
        g.ajouterNoeud(cN2);
        g.ajouterNoeud(cN3);
        g.ajouterNoeud(iN1);
        g.ajouterNoeud(iN2);
        g.ajouterNoeud(iN3);
        g.ajouterNoeud(iN4);
        g.ajouterNoeud(keanu);
        g.ajouterNoeud(fishburne);
        g.ajouterNoeud(attributAgeKeanu);
        g.ajouterNoeud(attributRoleKeanu);
        g.ajouterNoeud(attributAgeBurnes);
        g.ajouterNoeud(attributRoleBurnes);
        g.ajouterNoeud(attributDateSortieMatrix);
        g.ajouterNoeud(attributTitreMatrix);
        g.ajouterNoeud(attributGenreMatrix);
        g.ajouterNoeud(attribut2GenreMatrix);
        g.ajouterNoeud(attributGenreMatrix2);
        g.ajouterNoeud(attribut2GenreMatrix2);
        g.ajouterNoeud(attributDateSortieMatrix2);
        g.ajouterNoeud(attributTitreMatrix2);
        g.ajouterNoeud(attributDateSortieMatrix3);
        g.ajouterNoeud(attributTitreMatrix3);
        g.ajouterNoeud(attributDateSortieMatrixPS2);
        g.ajouterNoeud(attributTitreMatrixPS2);
        g.ajouterNoeud(attributGenreMatrixPS2);
        g.ajouterNoeud(attribut2GenreMatrixPS2);
        g.afficheGraphe();
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = g.convertToVisualGraph();
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
        JButton boutonCreerRelation = new JButton("Creer la relation entre les deux noeuds");
        boutonCreerRelation.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanelRelation panelRelation = new JPanelRelation(g, graph, boutonCreerRelation);
        JButton bouton2 = new JButton("Afficher graphe complet");
        bouton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton bouton3 = new JButton("Charger");
        bouton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton bouton4 = new JButton("Quitter");
        bouton4.setAlignmentX(Component.CENTER_ALIGNMENT);

        System.out.println(combo.getSelectedItem() + "");
        // listener sur la box
        combo.addActionListener(new ChoixTypeConceptActionListener(combo, panelChoixCreerNoeudConteneur,
                panelChoixCreerNoeudCourant, boutonCreerNoeud, graph, g, panelRelation));

        JPanelParcours panelParcours = new JPanelParcours(g, graph);
        // Création des réactions pour les bouttons
        boutonCreerNoeud
                .addActionListener(new CreerNoeudActionListener(panelChoixCreerNoeudCourant, g, graph, panelRelation));

        boutonCreerRelation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ici !");
            }
        });
        bouton2.addActionListener(new ActionListener() {
            Graphe gl = g;

            @Override
            public void actionPerformed(ActionEvent e) {
                gl.updateVisualGraph(graph);
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

        panelRelation.setMaximumSize(panelRelation.getPreferredSize());

        // Ajouts au panelChoixCreerNoeud
        panelChoixCreerNoeudConteneur.add(panelChoixCreerNoeudCourant);

        // Ajout des boutons au panel principal
        combo.setMaximumSize(combo.getPreferredSize());
        // ajout noeuds
        panel.add(labelAjoutNoeud);
        panel.add(combo);
        panelChoixCreerNoeudConteneur.setMaximumSize(panelChoixCreerNoeudConteneur.getPreferredSize());
        panel.add(panelChoixCreerNoeudConteneur);
        panel.add(boutonCreerNoeud);
        // relations
        panel.add(labelAjoutRelation);
        panel.add(panelRelation);
        panel.add(boutonCreerRelation);

        // Parcours
        panel.add(panelParcours);

        // Bouttons
        panel.add(bouton2);
        panel.add(bouton3);
        panel.add(bouton4);
        // Création de la fenetre et ajout des composants:
        maFenetre.setSize(1280, 720);
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFenetre.setLocationRelativeTo(null);
        maFenetre.add(panel, BorderLayout.EAST);
        maFenetre.add(view, BorderLayout.CENTER);
        maFenetre.setVisible(true);

        // TESTS
        System.out.println("-----------------TESTS---------------------");

        Graphe g2 = g.rechercheTouteRelation("rdfs:seeAlso", iN1, new Graphe());
        System.out.println("FINAL - >" + g2);

    }

    /**
     * renvoie un graphe contenant toutes les instances d'un concept donné
     * 
     * @param g          le graphique à rechercher
     * @param nomConcept le nom du concept que vous souhaitez rechercher
     * @return Un graphe contenant toutes les instances d'un concept.
     */
    public Graphe rechercheInstancesDeConcept(String nomConcept, boolean afficherAttributsInstances) {
        Graphe gRecherche = new Graphe();
        for (Noeud n : this.getGraphe()) {
            if (n.getTypeNoeud().equals("Instance")) {
                for (Noeud nRelie : n.getNoeudsRelie()) {
                    if (nRelie.getTypeNoeud().equals("Concept")) {
                        ConceptNoeud nc = (ConceptNoeud) nRelie;
                        if (nc.toString().equals(nomConcept)) {
                            if (!gRecherche.estDansGraphe(nc)) {
                                gRecherche.ajouterNoeud(nc);
                            }
                            gRecherche.ajouterNoeud(n);
                            if (afficherAttributsInstances) {
                                for (Noeud na : n.getNoeudsRelie()) {
                                    if (na.getTypeNoeud().equals("Attribut")) {
                                        AttributNoeud an = (AttributNoeud) na;
                                        gRecherche.ajouterNoeud(an);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return gRecherche;
    }

    /**
     * Il prend un nœud et un nom de relation comme paramètres, et renvoie un graphe
     * contenant le nœud et
     * tous les nœuds qui lui sont liés par la relation
     * 
     * @param noeudRecherche le nœud que nous recherchons
     * @param nomRelation    le nom de la relation que vous souhaitez rechercher
     * @return Un graphique
     */
    public Graphe rechercheLienNoeud(Noeud noeudRecherche, ArrayList<Relation> relations) {
        Graphe gRecherche = new Graphe();
        int cpt = 0;
        gRecherche.ajouterNoeud(noeudRecherche);
        for (Noeud n : noeudRecherche.getNoeudsRelie()) {
            for (int i = 0; i < relations.size(); i++) {
                if (noeudRecherche.getRelations().get(cpt).toString().equals(relations.get(i).toString())) {
                    if (!gRecherche.estDansGraphe(n)) {
                        gRecherche.ajouterNoeud(n);
                    }
                }
            }
            cpt++;
        }
        return gRecherche;
    }

    public Graphe rechercheTouteRelation(String nomRelation, Noeud noeudCourant, Graphe gRecherche) {
        if (!gRecherche.estDansGraphe(noeudCourant)) {
            gRecherche.ajouterNoeud(noeudCourant);
            for (int i = 0; i < noeudCourant.getRelations().size(); i++) {
                Relation rel = noeudCourant.getRelations().get(i);
                if (rel.toString().equals(nomRelation)) {
                    Graphe gIntermediaire = rechercheTouteRelation(nomRelation, rel.getNoeudArrive(), gRecherche);
                    gRecherche.concatGraphe(gIntermediaire);
                }
            }

        }
        // System.out.println("je sors de la fct");
        return gRecherche;
    }

    public void concatGraphe(Graphe g) {

        for (int i = 0; i < g.getGraphe().size(); i++) {
            Noeud noeud = g.getGraphe().get(i);
            if (!estDansGraphe(noeud)) {
                ajouterNoeud(noeud);
            }
        }
    }
    // public Graphe concatGraphe(Graphe g) {
    // Graphe res = this;
    // for (int i = 0; i < g.getGraphe().size(); i++) {
    // Noeud noeud = g.getGraphe().get(i);
    // if (!res.estDansGraphe(noeud)) {
    // res.ajouterNoeud(noeud);
    // }
    // }
    // return res;
    // }

    /**
     * renvoie la relation entre deux nœuds
     * 
     * @param n1 le premier nœud
     * @param n2 le nœud que nous voulons atteindre
     * @return La relation entre deux nœuds.
     */
    public Relation getRelationEntreNoeuds(Noeud n1, Noeud n2) {
        for (Relation r : n1.getRelations()) {
            if (r.getNoeudArrive().equals(n2)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (Noeud n : this.getGraphe()) {
            s += n.toString() + "\n";
        }
        return s;
    }

    public boolean estDansGraphe(Noeud n) {
        for (Noeud noeud : this.getGraphe()) {
            if (noeud.equals(n)) {
                return true;
            }
        }
        return false;
    }
}