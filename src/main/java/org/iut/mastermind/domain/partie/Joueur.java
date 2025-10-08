package org.iut.mastermind.domain.partie;

public record Joueur(String nom) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joueur(String nom1))) return false;
        return nom != null ? nom.equalsIgnoreCase(nom1) : nom1 == null;
    }



}
