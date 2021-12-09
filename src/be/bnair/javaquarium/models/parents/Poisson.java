package be.bnair.javaquarium.models.parents;

import be.bnair.javaquarium.models.EtreVivant;
import be.bnair.javaquarium.models.Sexe;
import be.bnair.javaquarium.models.Sexualite;

public class Poisson extends EtreVivant {
    private String nom;
    private Sexe sexe;
    private Sexualite sexualite;
    private boolean isReproducted;

    public Poisson(String nom, Sexe sexe, Sexualite sexualite, int age)
    {
        super(age);
        this.nom = nom;
        this.sexe = sexe;
        this.sexualite = sexualite;
        this.isReproducted = false;
    }

    public String getNom() {
        return this.nom;
    }
    public Sexe getSexe() {
        return this.sexe;
    }
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    public Sexualite GetSexualite() {
        return this.sexualite;
    }
    public boolean isReproducted() {
        return isReproducted;
    }
    public void setReproducted(boolean done) {
        this.isReproducted = done;
    }

    public int getAge() {
        return super.GetAge();
    }
}
