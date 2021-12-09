package be.bnair.javaquarium.models.enfants;

import be.bnair.javaquarium.models.Sexe;
import be.bnair.javaquarium.models.Sexualite;
import be.bnair.javaquarium.models.parents.Algue;
import be.bnair.javaquarium.models.parents.Poisson;

import java.util.Random;

public class Herbivores extends Poisson {
    private Race race;
    public enum Race
    {
        Sole, Bar, Carpe
    }
    public Herbivores(String nom, Sexe sexe, Race race, Sexualite sexualite)
    {
        super(nom, sexe, sexualite, new Random().nextInt(10) + 1);
        this.race = race;
    }

    public Herbivores(String nom, Sexe sexe, Race race, Sexualite sexualite, int age)
    {
        super(nom, sexe, sexualite, age);
        this.race = race;
    }
    public void Manger(Algue algue)
    {
        this.AjouterPointDeVie(3);
        algue.RemovePointDeVie(2);
    }
    public Race getRace()
    {
        return race;
    }
}
