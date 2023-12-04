package com.example.petitlingo.couleurs;

import java.util.Map;
import java.util.Random;

public class ColorPicker {

    public static String pickRandomColor(Map<String, Map<String, String>> colorsMap) {
        if (colorsMap.isEmpty()) {
            return "No colors available";
        }

        // Get a random color key
        String[] colorKeys = colorsMap.keySet().toArray(new String[0]);
        String randomColorKey = colorKeys[new Random().nextInt(colorKeys.length)];

        // Get the color code associated with the random key
        return getData(colorsMap, randomColorKey, "color");
    }

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
