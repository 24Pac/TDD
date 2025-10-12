package org.iut.mastermind.domain.proposition;

import java.util.Collections;
import java.util.List;

public class Reponse {
    private final List<Lettre> resultat;

    public Reponse(List<Lettre> resultat) {
        this.resultat = List.copyOf(resultat);
    }

    public Lettre lettre(int position) {
        if (position < 0 || position >= resultat.size()) {
            throw new IndexOutOfBoundsException("Position invalide : " + position);
        }
        return resultat.get(position);
    }

    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(Lettre.PLACEE::equals);
    }

    public List<Lettre> lettresResultat() {
        return Collections.unmodifiableList(resultat);
    }
}
