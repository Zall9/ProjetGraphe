package com.graphe;

import java.util.UUID;

public class AttributNoeud extends Noeud {
    private String valeur;

    public AttributNoeud(String valeur) {
        this.valeur = valeur;
        // super.typeNoeud = "Attribut";
        setTypeNoeud("Attribut");
        setId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return "" + this.valeur;
    }

}
