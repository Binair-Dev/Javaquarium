package be.bnair.javaquarium.models;

public class EtreVivant {

    protected int pointDeVie;
    protected int age;
    public EtreVivant()
    {
        this.pointDeVie = 10;
        this.age = 0;
    }

    public EtreVivant(int age)
    {
        this.pointDeVie = 10;
        this.age = age;
    }

    public int GetPointDeVie() {
        return pointDeVie;
    }
    public int GetAge() {
        return age;
    }
    public int SetPointDeVie(int ptv) {
        return pointDeVie = ptv;
    }
    public int AddAge() {
        return age += 1;
    }

    public void RemovePointDeVie(int amount)
    {
        pointDeVie -= amount;
    }

    public void AjouterPointDeVie(int amount)
    {
        this.pointDeVie += amount;
    }

    public boolean isAlive()
    {
        if (this.pointDeVie <= 0 || this.age >= 20)
            return false;
        else
            return true;
    }

    public int getAge() {
        return age;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }
}
