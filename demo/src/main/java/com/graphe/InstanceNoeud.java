package com.graphe;

import java.util.UUID;

public class InstanceNoeud extends Noeud {
    private String nomInstance;

    public InstanceNoeud(String nomInstance) {
        this.nomInstance = nomInstance;
        setId(UUID.randomUUID().toString());
        setTypeNoeud("Instance");
    }

    @Override
    public String toString() {
        return nomInstance;
    }

    String getNomInstance() {

        return nomInstance;
    }
}
