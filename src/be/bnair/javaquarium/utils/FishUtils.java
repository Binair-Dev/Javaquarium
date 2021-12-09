package be.bnair.javaquarium.utils;

import be.bnair.javaquarium.models.Sexe;
import be.bnair.javaquarium.models.Sexualite;

import java.util.Random;

public class FishUtils {

    public static Sexe getRandomSexe() {
        int rndNum = new Random().nextInt(2) + 1;
        Sexe sexe = null;
        switch (rndNum) {
            case 1:
                sexe = Sexe.MALE;
                break;
            case 2:
                sexe = Sexe.FEMELLE;
                break;
        }
        return sexe;
    }

    public static Sexualite getRandomSexualite() {
        int rndNumx = new Random().nextInt(3) + 1;
        Sexualite sexualite = null;
        switch (rndNumx) {
            case 1:
                sexualite = Sexualite.MONOSEXUE;
                break;
            case 2:
                sexualite = Sexualite.HERMAPHRODITE_AGE;
                break;
            case 3:
                sexualite = Sexualite.HERMAPHRODITE_OPPORTUNISTE;
                break;
        }
        return sexualite;
    }
}
