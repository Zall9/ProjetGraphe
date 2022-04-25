package com.graphe;

import java.util.UUID;

public class Relation {
    private TypeRelation typeDeRelation;
    private String valeur;
    private Noeud noeudDepart;
    private Noeud noeudArrive;
    private String ID;

    public Relation(TypeRelation type, String valeur, Noeud noeudDepart, Noeud noeudArrive) {
        this.typeDeRelation = type;
        this.valeur = valeur;
        this.noeudDepart = noeudDepart;
        this.noeudArrive = noeudArrive;
        this.ID = UUID.randomUUID().toString();
    }

    /**
     * Cette fonction retourne le type de relation de l'objet courant.
     * 
     * @returns Le type de relation.
     */
    TypeRelation getTypeDeRelation() {
        return this.typeDeRelation;
    }

    /**
     * Cette fonction fixe le type de relation de l'objet courant à la valeur passée
     * en paramètre.
     * 
     * @param {TypeRelation} value - La valeur à définir.
     */
    void setTypeDeRelation(TypeRelation value) {
        this.typeDeRelation = value;
    }

    /**
     * Il renvoie la valeur de l'attribut `valeur` de l'objet sur lequel il est
     * appelé
     * 
     * @returns La valeur de l'attribut valeur.
     */
    String getValeur() {
        return this.valeur;
    }

    /**
     * Il définit la valeur de l'attribut value à la valeur passée en paramètre
     * 
     * @param {String} value - La valeur du paramètre.
     */
    void setValeur(String value) {
        this.valeur = value;
    }

    @Override
    public String toString() {
        return getRelLabel();
    }

    public String getRelLabel() {

        return getTypeDeRelation().name() + ":" + getValeur();
    }

    public String getId() {
        return this.ID;
    }

    public Noeud getNoeudDepart() {
        return this.noeudDepart;
    }

    public void setNoeudDepart(Noeud noeudDepart) {
        this.noeudDepart = noeudDepart;
    }

    public Noeud getNoeudArrive() {
        return this.noeudArrive;
    }

    public void setNoeudArrive(Noeud noeudArrive) {
        this.noeudArrive = noeudArrive;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
