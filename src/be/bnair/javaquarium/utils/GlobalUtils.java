package be.bnair.javaquarium.utils;

import be.bnair.javaquarium.Main;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

public class GlobalUtils {

    public static void drawLogo()
    {
        System.out.println(ConsoleColor.RED + "BIENVENUE DANS LA SIMULATION DE VOTRE AQUARIUM" + ConsoleColor.RESET);
        System.out.println();
    }

    public static void showChoices() {
        System.out.println(" ");
        System.out.print(ConsoleColor.CYAN + "[");
        System.out.print(ConsoleColor.RED + "1");
        System.out.print(ConsoleColor.CYAN + "] Ajouter un tour | ");
        System.out.print(ConsoleColor.CYAN + "[");
        System.out.print(ConsoleColor.RED + "2");
        System.out.print(ConsoleColor.CYAN + "] Arreter la simulation | ");
        System.out.print(ConsoleColor.CYAN + "[");
        System.out.print(ConsoleColor.RED + "3");
        System.out.print(ConsoleColor.CYAN + "] Sauvegarder l'état actuel des poissons | ");
        System.out.print(ConsoleColor.CYAN + "[");
        System.out.print(ConsoleColor.RED + "4");
        System.out.println(ConsoleColor.CYAN + "] Acheter un poisson");
    }

    public final static void clearConsole()
    {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }

    public static void close()
    {
        System.exit(-1);
    }

    public static void save()
    {
        try
        {
            FileUtils fu = new FileUtils();
            String json = fu.serializeObject(Main.aquarium);
            File tosave = new File(Main.aquarium.getTours() + "-tour-save.json");
            if(!tosave.exists()) tosave.createNewFile();
            fu.save(tosave, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String capitalize(String str)
    {
        if(str == null) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String generateRandomName() {
        String username = null;
        String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "r", "s", "t", "v", "w", "x", "y", "z" };
        String[] vowels = { "a", "e", "i", "o", "u" };
        String word = "";
        boolean b = true;
        int amount = new Random().nextInt(10) + 1;
        for (int i = 0; i < (amount < 5 ? 5 : amount); i++)
        {
            word += b ? consonants[new Random().nextInt(consonants.length)] : vowels[new Random().nextInt(vowels.length)];
            b = !b;
        }
        username = word;
        return capitalize(username);
    }
}
