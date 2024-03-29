package com.graphe;

import java.util.ArrayList;
import java.util.List;

public class Noeud {

    private List<Noeud> noeudsRelie = new ArrayList<Noeud>();
    private List<Relation> relations = new ArrayList<Relation>();
    private String ID;
    protected String typeNoeud;

    /**
     * Cette fonction retourne la liste des nœuds connectés au nœud courant
     * 
     * @returns La liste des nœuds connectés au nœud actuel.
     */
    List<Noeud> getNoeudsRelie() {
        return this.noeudsRelie;
    }

    /**
     * Renvoie la liste des relations.
     * 
     * @returns Une liste de relations.
     */
    List<Relation> getRelations() {
        return this.relations;
    }

    public Relation getRelationAtIndex(int index) {
        List<Relation> c = getRelations();
        return c.get(index);
    }

    /**
     * ajoute un nœud et une relation à la liste des nœuds et des relations du nœud
     * courant
     * 
     * @param noeudRelie le nœud qui est connecté au nœud actuel
     * @param relation   La relation entre les deux nœuds.
     */
    public void relieNoeuds(Noeud noeudRelie, Relation relation) {
        this.noeudsRelie.add(noeudRelie);
        this.relations.add(relation);
    }

    /**
     * imprime toutes les relations de l'objet courant
     */
    public void afficheRelations() {
        for (Relation r : this.relations) {
            System.out.println(r);
        }
    }

    public String getId() {
        return this.ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getTypeNoeud() {
        return this.typeNoeud;
    }

    public void setTypeNoeud(String typeNoeud) {
        this.typeNoeud = typeNoeud;
    }

    public ArrayList<String> getRelationsNames() {
        ArrayList<String> relationsNames = new ArrayList<String>();
        for (Relation r : this.relations) {
            relationsNames.add(r.getRelLabel());
        }
        return relationsNames;
    }
}