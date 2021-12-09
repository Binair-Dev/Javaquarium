package be.bnair.javaquarium.models;

import be.bnair.javaquarium.Main;
import be.bnair.javaquarium.models.enfants.Carnivores;
import be.bnair.javaquarium.models.enfants.Herbivores;
import be.bnair.javaquarium.models.parents.Algue;
import be.bnair.javaquarium.models.parents.Poisson;
import be.bnair.javaquarium.utils.ConsoleColor;
import be.bnair.javaquarium.utils.GlobalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium
{
    private List<Poisson> poissons;
    private List<Algue> algues;
    private int tour;
    private int max_poisson;
    private int max_algue;

    public Aquarium()
    {
        this.poissons = new ArrayList<Poisson>();
        this.algues = new ArrayList<Algue>();
        this.tour = 0;
        this.max_poisson = 200;
        this.max_algue = 200;
    }

    public void AjouterPoisson(Poisson fish)
    {
        System.out.println("Le poisson " + fish.getNom() + " vient d'être ajouté a l'aquarium !");
        this.poissons.add(fish);
    }

    public void RetirerPoisson(Poisson fish)
    {
        this.poissons.remove(fish);
    }
    public void AjouterAlgue(Algue algue) {
        this.algues.add(algue);
    }
    public void RetirerAlgue(Algue algue) {
        this.algues.remove(algue);
    }
    public int getTours() {
        return this.tour;
    }
    public void AjouterTour()
    {
        tour++;
        removeDeathFish();
        removeDeathAlgue();

        UpdateSexe();
        updateAlgueLife();
        updateFishLife();

        Manger();
        Reproduire();
        updateFishAge();

        LogInformations();
    }

    private void UpdateSexe()
    {
        for(int i  = 0; i < GetPoissons().size(); i++)
        {
            Poisson poisson = GetPoissons().get(i);
            if(poisson.GetSexualite() == Sexualite.HERMAPHRODITE_AGE)
            {
                if(poisson.GetAge() == 10)
                {
                    switch (poisson.getSexe().toString())
                    {
                        case "MALE":
                            poisson.setSexe(Sexe.FEMELLE);
                            break;
                        case "FEMELLE":
                            poisson.setSexe(Sexe.MALE);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void Reproduire()
    {
        int size = GetAlgues().size();
        for(int i = 0; i < size; i++)
        {
            Algue algue = GetAlgues().get(i);
            if (algue.GetPointDeVie() >= 10)
            {
                if (GetAlgues().size() < max_algue)
                {
                    algue.RemovePointDeVie(5);
                    Algue splitted = new Algue(5);
                    splitted.RemovePointDeVie(5);
                    this.AjouterAlgue(splitted);
                }
            }
        }
        for(int i  = 0; i < GetPoissons().size(); i++)
        {
            Poisson poisson = GetPoissons().get(i);
            if(this.GetPoissons().size() < max_poisson)
            {
                if (poisson.GetPointDeVie() >= 5 && poisson.GetPointDeVie() < 15)
                {
                    if (poisson instanceof Carnivores)
                    {
                        Carnivores carnivores = (Carnivores)poisson;
                        Poisson choosen = this.GetPoissons().get(new Random().nextInt(this.GetPoissons().size()));
                        if (choosen instanceof Carnivores)
                        {
                            Carnivores carnivoresChoosen = (Carnivores)choosen;
                            if (carnivores.getRace().equals(carnivoresChoosen.getRace()) && !carnivores.equals(carnivoresChoosen))
                            {
                                if(!carnivoresChoosen.isReproducted() && !carnivores.isReproducted()) {
                                    carnivores.setReproducted(true);
                                    carnivoresChoosen.setReproducted(true);
                                    int sx = new Random().nextInt(10);
                                    int sexualite = new Random().nextInt(3);
                                    Sexe sexe = sx >= 5 ? Sexe.MALE : Sexe.FEMELLE;
                                    Sexualite sexualite1 = Sexualite.MONOSEXUE;
                                    switch (sexualite)
                                    {
                                        case 1:
                                            sexualite1 = Sexualite.MONOSEXUE;
                                            break;
                                        case 2:
                                            sexualite1 = Sexualite.HERMAPHRODITE_AGE;
                                            break;
                                        case 3:
                                            sexualite1 = Sexualite.HERMAPHRODITE_OPPORTUNISTE;
                                            break;
                                        default:
                                            break;
                                    }
                                    if (carnivores.getSexe() != carnivoresChoosen.getSexe())
                                    {
                                        Poisson p = new Carnivores(GlobalUtils.generateRandomName(), sexe, carnivores.getRace(), sexualite1);
                                        this.GetPoissons().add(p);
                                        System.out.println(ConsoleColor.CYAN + carnivores.getNom() + " vient d'avoir une bébé avec " + carnivoresChoosen.getNom() + " du nom de: " + p.getNom() + ConsoleColor.RESET);
                                        try {
                                            Thread.sleep(200);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else
                                    {
                                        switch (carnivoresChoosen.getSexe().toString())
                                        {
                                            case "MALE":
                                                carnivores.setSexe(Sexe.FEMELLE);
                                                break;
                                            case "FEMELLE":
                                                carnivores.setSexe(Sexe.MALE);
                                                break;
                                            default:
                                                break;
                                        }
                                        Poisson p = new Carnivores(GlobalUtils.generateRandomName(), sexe, carnivores.getRace(), sexualite1);
                                        this.GetPoissons().add(p);
                                        System.out.println(carnivores.getNom() + " vient d'avoir une bébé avec " + carnivoresChoosen.getNom() + " du nom de: " + p.getNom());
                                    }
                                }
                            }
                        }
                    } else {
                        Herbivores carnivores = (Herbivores)poisson;
                        Poisson choosen = this.GetPoissons().get(new Random().nextInt(this.GetPoissons().size()));
                        if (choosen instanceof Herbivores)
                        {
                            Herbivores carnivoresChoosen = (Herbivores)choosen;
                            if (carnivores.getRace().equals(carnivoresChoosen.getRace()) && !carnivores.equals(carnivoresChoosen))
                            {
                                if(!carnivoresChoosen.isReproducted() && !carnivores.isReproducted()) {
                                    if((carnivores.getAge() > 5 && carnivoresChoosen.getAge() > 5) && carnivores.getAge() < 18 && carnivoresChoosen.getAge() < 18 ) {
                                        carnivores.setReproducted(true);
                                        carnivoresChoosen.setReproducted(true);
                                        int sx = new Random().nextInt(10);
                                        int sexualite = new Random().nextInt(3);
                                        Sexe sexe = sx >= 5 ? Sexe.MALE : Sexe.FEMELLE;
                                        Sexualite sexualite1 = Sexualite.MONOSEXUE;
                                        switch (sexualite)
                                        {
                                            case 1:
                                                sexualite1 = Sexualite.MONOSEXUE;
                                                break;
                                            case 2:
                                                sexualite1 = Sexualite.HERMAPHRODITE_AGE;
                                                break;
                                            case 3:
                                                sexualite1 = Sexualite.HERMAPHRODITE_OPPORTUNISTE;
                                                break;
                                            default:
                                                break;
                                        }
                                        if (carnivores.getSexe() != carnivoresChoosen.getSexe())
                                        {
                                            Poisson p = new Herbivores(GlobalUtils.generateRandomName(), sexe, carnivores.getRace(), sexualite1);
                                            this.GetPoissons().add(p);
                                            System.out.println(carnivores.getNom() + " vient d'avoir une bébé avec " + carnivoresChoosen.getNom() + " du nom de: " + p.getNom());
                                        }
                                        else
                                        {
                                            switch (carnivoresChoosen.getSexe().toString())
                                            {
                                                case "MALE":
                                                    carnivores.setSexe(Sexe.FEMELLE);
                                                    break;
                                                case "FEMELLE":
                                                    carnivores.setSexe(Sexe.MALE);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            Poisson p = new Herbivores(GlobalUtils.generateRandomName(), sexe, carnivores.getRace(), sexualite1);
                                            this.GetPoissons().add(p);
                                            System.out.println(carnivores.getNom() + " vient d'avoir une bébé avec " + carnivoresChoosen.getNom() + " du nom de: " + p.getNom());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public List<Poisson> GetPoissons() {
        return poissons;
    }
    public List<Algue> GetAlgues() {
        return algues;
    }

    public Poisson getPoissonByName(String name)
    {
        for(Poisson p : GetPoissons())
        {
            if (p.getNom().equals(name))
                return p;
        }
        return null;
    }


    public void LogInformations()
    {
        if(Main.isDebugMode) {
            int car = 0;
            int herb = 0;
            for(Object obj : poissons.toArray()) {
                Poisson poisson = (Poisson) obj;
                poisson.setReproducted(false);
                System.out.print(ConsoleColor.GREEN_BRIGHT + "Nom: " + poisson.getNom() + " | Vie:" + poisson.getPointDeVie() + " | Age:" + poisson.getAge());
                if(poisson instanceof Carnivores) {
                    car++;
                    Carnivores c = (Carnivores) poisson;
                    System.out.println(ConsoleColor.GREEN_BRIGHT + " | Race: " + c.getRace() + " | Sexe: " + c.getSexe() + ConsoleColor.RESET);
                }
                else {
                    herb++;
                    Herbivores c = (Herbivores) poisson;
                    System.out.println(" | Race: " + c.getRace() + " | Sexe: " + c.getSexe());
                }
            }
            System.out.println(ConsoleColor.GREEN_BRIGHT + "Vous avez " + ConsoleColor. RED + car + ConsoleColor.GREEN_BRIGHT + " Poissons carnivores et " + ConsoleColor.RED + herb + ConsoleColor.GREEN_BRIGHT +" poissons herbivores" + ConsoleColor.RESET);
        }
        System.out.println(ConsoleColor.BLACK_BRIGHT + "Nombres d'algues: " + ConsoleColor.RED + this.GetAlgues().size() + ConsoleColor.RESET);
        System.out.println(ConsoleColor.BLACK_BRIGHT + "Nombres de poissons: " + ConsoleColor.RED + this.GetPoissons().size() + ConsoleColor.RESET);
    }

    private void Manger()
    {
        for(Object obj : GetPoissons().toArray())
        {
            if(GetPoissons().toArray().length <= 2) return;
            Poisson p = (Poisson) obj;
            if (p instanceof Carnivores)
            {
                if(p.getPointDeVie() <= 5) {
                    if(!GetPoissons().contains(p)) break;
                    Carnivores carnivore = ((Carnivores)p);
                    Poisson toEat = getFishToEat(carnivore);

                    if(GetPoissons().contains(toEat)) {
                        if(toEat instanceof Carnivores) {
                            Carnivores choosed = (Carnivores) toEat;
                            if(!carnivore.getRace().equals(choosed.getRace())) {
                                if(carnivore.Manger(toEat, this)) {
                                    System.out.println(ConsoleColor.RED + carnivore.getNom() + " vient de manger  " + toEat.getNom() + ConsoleColor.RESET);
                                    System.out.println(ConsoleColor.PURPLE + toEat.getNom() + " vient de mourrir !" + ConsoleColor.RESET);
                                }
                                else
                                    System.out.println(ConsoleColor.RED + carnivore.getNom() + " vient de tenter manger " + toEat.getNom() + " mais a échoué!" + ConsoleColor.RESET);
                            }
                            else {
                                System.out.println(ConsoleColor.RED + carnivore.getNom() + " n'a rien trouvé a manger.." + ConsoleColor.RESET);
                            }
                        }
                    }
                }
            }
            else
            {
                if (this.GetAlgues().size() > 1 && getPoissonByName(p.getNom()) != null && p.getPointDeVie() <= 7)
                {
                    Herbivores herbivores = (Herbivores)p;
                    herbivores.Manger(this.GetAlgues().get(new Random().nextInt(this.GetAlgues().size())));
                    System.out.println(ConsoleColor.GREEN + herbivores.getNom() + " vient de manger un morceau algue" + ConsoleColor.RESET);
                }
            }
        }
    }

    public boolean Pourcentage(double amount)
    {
        Random rand = new Random();
        int chance = rand.nextInt(101 -1) + 1;

        if (chance >= amount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void updateAlgueLife()
    {
        for(int i = 0; i < GetAlgues().size(); i++)
        {
            Algue algue = GetAlgues().get(i);
            algue.AjouterPointDeVie(1);
        }
    }

    private void updateFishLife()
    {
        for(int i  = 0; i < GetPoissons().size(); i++)
        {
            Poisson poisson = GetPoissons().get(i);
            poisson.RemovePointDeVie(1);
        }
    }

    private void removeDeathAlgue()
    {
        for(int i = 0; i < GetAlgues().size(); i++)
        {
            Algue algue = GetAlgues().get(i);
            if (!(algue.isAlive()))
            {
                this.GetAlgues().remove(i);
            }
        }
    }

    private void removeDeathFish()
    {
        for(int i = 0; i < GetPoissons().size(); i++)
        {
            Poisson poisson = GetPoissons().get(i);
            if (!poisson.isAlive())
            {
                System.out.println(ConsoleColor.PURPLE_BRIGHT + "Le poisson " + poisson.getNom() + " vient de mourrir !" + ConsoleColor.RESET);
                this.GetPoissons().remove(i);
            }
        }
    }

    public Poisson getFishToEat(Carnivores poisson)
    {
        Poisson choosen = null;
        choosen = this.GetPoissons().get(new Random().nextInt(this.GetPoissons().size()));
        if (choosen instanceof Carnivores)
        {
            Carnivores c = (Carnivores)choosen;
            if (poisson.getRace() != c.getRace() && poisson != choosen)
            {
                return choosen;
            }
        }
        else
        {
            return choosen;
        }
        return choosen;
    }
    private void updateFishAge()
    {
        for(int i  = 0; i < GetPoissons().size(); i++)
        {
            Poisson poisson = GetPoissons().get(i);
            poisson.AddAge();
        }
    }
}
