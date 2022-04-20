package com.graphe;

import java.util.UUID;

public class ConceptNoeud extends Noeud {
    private TypeConcept concept;
    private String nomComplet;

    public ConceptNoeud(TypeConcept concept, String nomComplet) {
        this.concept = concept;
        this.nomComplet = nomComplet;
        setId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return concept.name() + ":" + nomComplet;
    }
}
