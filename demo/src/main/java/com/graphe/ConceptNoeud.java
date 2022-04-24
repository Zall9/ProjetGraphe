package com.graphe;

import java.util.UUID;

public class ConceptNoeud extends Noeud {
    private TypeConcept typeConcept;
    private String nomConcept;

    public ConceptNoeud(TypeConcept typeConcept, String nomConcept) {
        this.typeConcept = typeConcept;
        this.nomConcept = nomConcept;
        super.setTypeNoeud("Concept");
        setId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return typeConcept.name() + ":" + nomConcept;
    }

    TypeConcept getTypeConcept() {
        return typeConcept;
    }

    String getNomConcept() {
        return nomConcept;
    }
}
