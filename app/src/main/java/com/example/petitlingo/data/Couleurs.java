package com.example.petitlingo.data;

import java.util.HashMap;
import java.util.Map;

public class Couleurs {
    public static void main(String[] args) {
        // Créer le tableau associatif en Java
        Map<String, Map<String, String>> colorsMap = new HashMap<>();

        // Ajouter les données au tableau
        addData(colorsMap, "rouge", "@color/colorRed", "red");
        addData(colorsMap, "bleu", "@color/colorBlue", "blue");
        addData(colorsMap, "jaune", "@color/colorYellow", "yellow");
        addData(colorsMap, "vert", "@color/colorGreen", "green");

        // Accéder aux données dans le tableau
        String redColor = getData(colorsMap, "rouge", "color");
        String blueText = getData(colorsMap, "bleu", "text");

        // Afficher les données
        System.out.println("Color for rouge: " + redColor);
        System.out.println("Text for bleu: " + blueText);
    }

    // Méthode pour ajouter des données au tableau associatif
    private static void addData(Map<String, Map<String, String>> colorsMap,
                                String key, String color, String text) {
        Map<String, String> colorData = new HashMap<>();
        colorData.put("color", color);
        colorData.put("text", text);
        colorsMap.put(key, colorData);
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
