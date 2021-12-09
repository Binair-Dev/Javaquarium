package be.bnair.javaquarium;

import be.bnair.javaquarium.models.Aquarium;
import be.bnair.javaquarium.models.Sexe;
import be.bnair.javaquarium.models.Sexualite;
import be.bnair.javaquarium.models.enfants.Carnivores;
import be.bnair.javaquarium.models.enfants.Herbivores;
import be.bnair.javaquarium.models.parents.Poisson;
import be.bnair.javaquarium.utils.ConsoleColor;
import be.bnair.javaquarium.utils.FileUtils;
import be.bnair.javaquarium.utils.GlobalUtils;
import be.bnair.javaquarium.utils.WindowsUtils;

import java.util.Scanner;

public class Main {
    public static boolean isDebugMode = true;
    public static Aquarium aquarium = new Aquarium();

    public static void main(String[] args) {
        WindowsUtils.init();
        GlobalUtils.drawLogo();

        FileUtils.readConfigFileLineByLine();

        for(int i = 0; i < 15; i++) {
            System.out.println("Tour " + i);
            aquarium.AjouterTour();
        }

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while(isRunning) {
            GlobalUtils.showChoices();
            String resp = scanner.next();
            switch (resp) {
                case "1":
                    aquarium.AjouterTour();
                    break;
                case "2":
                    GlobalUtils.close();
                    break;
                case "3":
                    GlobalUtils.save();
                    break;
                case "4":
                    System.out.print(ConsoleColor.CYAN + "[");
                    System.out.print(ConsoleColor.RED + "1");
                    System.out.print(ConsoleColor.CYAN + "] Male | ");
                    System.out.print(ConsoleColor.CYAN + "[");
                    System.out.print(ConsoleColor.RED + "2");
                    System.out.println(ConsoleColor.CYAN + "] Femelle");
                    Sexe sexe = null;
                    while(sexe == null) {
                        switch (scanner.next()) {
                            case "1":
                                sexe = Sexe.MALE;
                                break;
                            case "2":
                                sexe = Sexe.FEMELLE;
                                break;
                            default:
                                System.out.println(ConsoleColor.RED + "Veuillez entrer un chiffre valide !");
                                break;
                        }
                    }
                    System.out.print(ConsoleColor.CYAN + "[");
                    System.out.print(ConsoleColor.RED + "1");
                    System.out.print(ConsoleColor.CYAN + "] Carnivor | ");
                    System.out.print(ConsoleColor.CYAN + "[");
                    System.out.print(ConsoleColor.RED + "2");
                    System.out.println(ConsoleColor.CYAN + "] Herbivore");
                    String type = null;
                    while(type == null) {
                        switch (scanner.next()) {
                            case "1":
                                type = "C";
                                break;
                            case "2":
                                type = "H";
                                break;
                            default:
                                System.out.println(ConsoleColor.RED + "Veuillez entrer un chiffre valide !");
                                break;
                        }
                    }
                    if(type.equals("C")) {
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "1");
                        System.out.print(ConsoleColor.CYAN + "] Mérou | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "2");
                        System.out.print(ConsoleColor.CYAN + "] Thon | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "3");
                        System.out.println(ConsoleColor.CYAN + "] PoissonClown");
                        Carnivores.Race race = null;
                        while(race == null) {
                            switch (scanner.next()) {
                                case "1":
                                    race = Carnivores.Race.Mérou;
                                    break;
                                case "2":
                                    race = Carnivores.Race.Thon;
                                    break;
                                case "3":
                                    race = Carnivores.Race.PoissonClown;
                                    break;
                                default:
                                    System.out.println(ConsoleColor.RED + "Veuillez entrer un chiffre valide !");
                                    break;
                            }
                        }
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "1");
                        System.out.print(ConsoleColor.CYAN + "] Monosexuel | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "2");
                        System.out.print(ConsoleColor.CYAN + "] Hermaphrodite Age | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "3");
                        System.out.println(ConsoleColor.CYAN + "] Hermaphrodite Opportuniste");
                        String sexualite = null;
                        while(sexualite == null) {
                            switch (scanner.next()) {
                                case "1":
                                    sexualite = Sexualite.MONOSEXUE.toString();
                                    Poisson p1 = new Carnivores(GlobalUtils.generateRandomName(), sexe, race, Sexualite.MONOSEXUE);
                                    aquarium.AjouterPoisson(p1);
                                    aquarium.LogInformations();
                                    break;
                                case "2":
                                    sexualite = Sexualite.HERMAPHRODITE_AGE.toString();
                                    Poisson p2 = new Carnivores(GlobalUtils.generateRandomName(), sexe, race, Sexualite.HERMAPHRODITE_AGE);
                                    aquarium.AjouterPoisson(p2);
                                    aquarium.LogInformations();
                                    break;
                                case "3":
                                    sexualite = Sexualite.HERMAPHRODITE_OPPORTUNISTE.toString();
                                    Poisson p3 = new Carnivores(GlobalUtils.generateRandomName(), sexe, race, Sexualite.HERMAPHRODITE_OPPORTUNISTE);
                                    aquarium.AjouterPoisson(p3);
                                    aquarium.LogInformations();
                                    break;
                                default:
                                    System.out.println(ConsoleColor.RED + "Veuillez entrer un chiffre valide !");
                                    break;
                            }
                        }
                    }
                    if(type.equals("H")) {
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "1");
                        System.out.print(ConsoleColor.CYAN + "] Sole | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "2");
                        System.out.print(ConsoleColor.CYAN + "] Bar | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "3");
                        System.out.println(ConsoleColor.CYAN + "] Carpe");
                        Herbivores.Race race = null;
                        while(race == null) {
                            switch (scanner.next()) {
                                case "1":
                                    race = Herbivores.Race.Sole;
                                    break;
                                case "2":
                                    race = Herbivores.Race.Bar;
                                    break;
                                case "3":
                                    race = Herbivores.Race.Carpe;
                                    break;
                                default:
                                    System.out.println(ConsoleColor.RED + "Veuillez entrer un chiffre valide !");
                                    break;
                            }
                        }
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "1");
                        System.out.print(ConsoleColor.CYAN + "] Monosexuel | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "2");
                        System.out.print(ConsoleColor.CYAN + "] Hermaphrodite Age | ");
                        System.out.print(ConsoleColor.CYAN + "[");
                        System.out.print(ConsoleColor.RED + "3");
                        System.out.println(ConsoleColor.CYAN + "] Hermaphrodite Opportuniste");
                        String sexualite = null;
                        while(sexualite == null) {
                            switch (scanner.next()) {
                                case "1":
                                    sexualite = Sexualite.MONOSEXUE.toString();
                                    Poisson p1 = new Herbivores(GlobalUtils.generateRandomName(), sexe, race, Sexualite.MONOSEXUE);
                                    aquarium.AjouterPoisson(p1);
                                    aquarium.LogInformations();
                                    break;
                                case "2":
                                    sexualite = Sexualite.HERMAPHRODITE_AGE.toString();
                                    Poisson p2 = new Herbivores(GlobalUtils.generateRandomName(), sexe, race, Sexualite.HERMAPHRODITE_AGE);
                                    aquarium.AjouterPoisson(p2);
                                    aquarium.LogInformations();
                                    break;
                                case "3":
                                    sexualite = Sexualite.HERMAPHRODITE_OPPORTUNISTE.toString();
                                    Poisson p3 = new Herbivores(GlobalUtils.generateRandomName(), sexe, race, Sexualite.HERMAPHRODITE_OPPORTUNISTE);
                                    aquarium.AjouterPoisson(p3);
                                    aquarium.LogInformations();
                                    break;
                                default:
                                    System.out.println(ConsoleColor.RED + "Veuillez entrer un chiffre valide !");
                                    break;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println(ConsoleColor.RED + "Commande invalide !" + ConsoleColor.RESET);
                    break;
            }
        }
    }
}
