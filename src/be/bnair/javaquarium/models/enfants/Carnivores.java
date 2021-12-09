package be.bnair.javaquarium.models.enfants;

import be.bnair.javaquarium.models.Aquarium;
import be.bnair.javaquarium.models.Sexe;
import be.bnair.javaquarium.models.Sexualite;
import be.bnair.javaquarium.models.parents.Poisson;
import be.bnair.javaquarium.utils.ConsoleColor;

import java.util.Random;

public class Carnivores extends Poisson {
    private Race race;
    public enum Race
    {
        Mérou, Thon, PoissonClown
    }
    public Carnivores(String nom, Sexe sexe, Race race, Sexualite sexualite)
    {
        super(nom, sexe, sexualite, new Random().nextInt(10) + 1);
        this.race = race;
    }
    public Carnivores(String nom, Sexe sexe, Race race, Sexualite sexualite, int age)
    {
        super(nom, sexe, sexualite, age);
        this.race = race;
    }
    public boolean Manger(Poisson poisson, Aquarium aquarium)
    {
        if (aquarium.Pourcentage(30))
        {
            if (aquarium.getPoissonByName(poisson.getNom()) != null) {
                aquarium.RetirerPoisson(poisson);
            }
            this.AjouterPointDeVie(5);
            return true;
        }
        else {
            if (aquarium.getPoissonByName(this.getNom()) != null && this.getPointDeVie() <= 0) {
                aquarium.RetirerPoisson(this);
            } else this.RemovePointDeVie(4);

            return false;
        }
    }

    public Race getRace()
    {
        return race;
    }
}
