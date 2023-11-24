package com.example.petitlingo.couleurs;

import java.util.Map;
import java.util.Random;

public class ColorPicker {

    // Méthode pour choisir une couleur aléatoire
    public static String pickRandomColor(Map<String, Map<String, String>> colorsMap) {
        // Vérifier si le tableau des couleurs n'est pas vide
        if (colorsMap.isEmpty()) {
            return "No colors available";
        }

        // Générer un index aléatoire
        int randomIndex = new Random().nextInt(colorsMap.size());

        // Récupérer la clé à l'index aléatoire
        String randomColorKey = (String) colorsMap.keySet().toArray()[randomIndex];

        // Récupérer la couleur associée à la clé

        return getData(colorsMap, randomColorKey, "color");
    }

    // Méthode pour obtenir des données du tableau associatif
    private static String getData(Map<String, Map<String, String>> colorsMap,
                                  String key, String subKey) {
        if (colorsMap.containsKey(key)) {
            Map<String, String> colorData = colorsMap.get(key);
            if (colorData.containsKey(subKey)) {
                return colorData.get(subKey);
            }
        }
        return "Not found";
    }
}
