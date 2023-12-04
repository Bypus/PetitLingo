package com.example.petitlingo.animauxLvls;
import androidx.lifecycle.ViewModel;

public class AnimalViewModel extends ViewModel {
    private boolean elephantInPlace, lionInPlace, cowInPlace, giraffeInPlace = false;

    // Méthode pour vérifier si l'éléphant est à sa place
    public boolean isElephantInPlace() {
        return elephantInPlace;
    }

    // Méthode pour définir si l'éléphant est à sa place
    public void setElephantInPlace(boolean elephantInPlace) {
        this.elephantInPlace = elephantInPlace;
    }

    // Méthode pour vérifier si le lion est à sa place
    public boolean isLionInPlace() {
        return lionInPlace;
    }

    // Méthode pour définir si le lion est à sa place
    public void setLionInPlace(boolean lionInPlace) {
        this.lionInPlace = lionInPlace;
    }

    // Méthode pour vérifier si la vache est à sa place
    public boolean isCowInPlace() {
        return cowInPlace;
    }

    // Méthode pour définir si la vache est à sa place
    public void setCowInPlace(boolean cowInPlace) {
        this.cowInPlace = cowInPlace;
    }

    // Méthode pour vérifier si la girafe est à sa place
    public boolean isGiraffeInPlace() {
        return giraffeInPlace;
    }

    // Méthode pour définir si la girafe est à sa place
    public void setGiraffeInPlace(boolean giraffeInPlace) {
        this.giraffeInPlace = giraffeInPlace;
    }
}
