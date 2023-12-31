package com.example.petitlingo.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Couleurs {
    public Map<String, Map<String, String>> tabColors(){
        Map<String, Map<String, String>> colorsMap = new HashMap<>();

        // Ajouter les données au tableau
        addData(colorsMap, "rouge", "#F44336", "Red");
        addData(colorsMap, "bleu", "#03A9F4", "Blue");
        addData(colorsMap, "jaune", "#FFEB3B", "Yellow");
        addData(colorsMap, "vert", "#8BC34A", "Green");
        addData(colorsMap, "violet", "#852EEB", "Purple");
        addData(colorsMap, "marron", "#774B2C", "Brown");

        // Accéder aux données dans le tableau
        String redColor = getData(colorsMap, "rouge", "color");
        String blueText = getData(colorsMap, "bleu", "text");

        // Afficher les données
        System.out.println("Color for rouge: " + redColor);
        System.out.println("Text for bleu: " + blueText);

        return colorsMap;
    }

    // Méthode pour ajouter des données au tableau associatif
    public static void addData(Map<String, Map<String, String>> colorsMap,
                                String key, String color, String text) {
        Map<String, String> colorData = new HashMap<>();
        colorData.put("color", color);
        colorData.put("text", text);
        colorsMap.put(key, colorData);
    }

    // Méthode pour obtenir des données du tableau associatif
    public static String getData(Map<String, Map<String, String>> colorsMap,
                                  String key, String subKey) {
        if (colorsMap.containsKey(key)) {
            Map<String, String> colorData = colorsMap.get(key);
            if (colorData.containsKey(subKey)) {
                return colorData.get(subKey);
            }
        }
        return "Not found";
    }

    //Méthode pour prendre des couleurs aléatoirement
    public static String pickRandomColor(Map<String, Map<String, String>> colorsMap) {
        if (colorsMap.isEmpty()) {
            return "No colors available";
        }

        // Prend une clé aléatoire
        String[] colorKeys = colorsMap.keySet().toArray(new String[0]);
        String randomColorKey = colorKeys[new Random().nextInt(colorKeys.length)];

        // Prend la couleur associée à la clé
        return getData(colorsMap, randomColorKey, "color");
    }

    //Méthode pour obtenir le nom de la couleur à partir de son code
    public static String getTextForColorCode(Map<String, Map<String, String>> colorsMap, String colorCode) {
        for (Map.Entry<String, Map<String, String>> entry : colorsMap.entrySet()) {
            Map<String, String> colorData = entry.getValue();
            if (colorData.containsKey("color") && colorData.get("color").equalsIgnoreCase(colorCode)) {
                return colorData.get("text");
            }
        }
        return "Not found";
    }
}
