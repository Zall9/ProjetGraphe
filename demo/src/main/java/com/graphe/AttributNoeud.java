package com.graphe;

import java.util.Random;
import java.util.UUID;

public class AttributNoeud extends Noeud {
    private String valeur;

    public AttributNoeud(String valeur) {
        byte[] b = new byte[20];
        new Random().nextBytes(b);
        UUID uuid= UUID.nameUUIDFromBytes(b);
        this.valeur = valeur;
        setId(uuid.toString()); 
    }

    @Override
    public String toString() {
        return "" + this.valeur;
    }
}
