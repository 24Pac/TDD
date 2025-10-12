package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotSecret {
    private final String mot;

    public MotSecret(String mot) {
        if (mot == null || mot.isEmpty()) {
            throw new IllegalArgumentException("Le mot secret ne peut pas être vide");
        }
        this.mot = mot.toUpperCase();
    }

    public Reponse compareProposition(String essai) {
        if (essai == null || essai.length() != mot.length()) {
            throw new IllegalArgumentException("L'essai doit avoir la même longueur que le mot secret");
        }

        essai = essai.toUpperCase();
        List<Lettre> resultat = new ArrayList<>();
        Map<Character, Long> lettresDisponibles = compterLettresNonPlacees(essai);

        for (int i = 0; i < essai.length(); i++) {
            char lettre = essai.charAt(i);
            resultat.add(evaluerLettre(lettre, i, lettresDisponibles));
        }

        return new Reponse(resultat);
    }

    private Map<Character, Long> compterLettresNonPlacees(String essai) {
        Map<Character, Long> disponibles = new HashMap<>();
        for (int i = 0; i < mot.length(); i++) {
            char lettreSecret = mot.charAt(i);
            if (i >= essai.length() || essai.charAt(i) != lettreSecret) {
                disponibles.merge(lettreSecret, 1L, Long::sum);
            }
        }
        return disponibles;
    }

    private Lettre evaluerLettre(char lettre, int position, Map<Character, Long> disponibles) {
        if (mot.charAt(position) == lettre) {
            return Lettre.PLACEE;
        }

        if (disponibles.getOrDefault(lettre, 0L) > 0) {
            disponibles.merge(lettre, -1L, Long::sum);
            return Lettre.NON_PLACEE;
        }

        return Lettre.INCORRECTE;
    }
}
