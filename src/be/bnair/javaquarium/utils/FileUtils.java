package be.bnair.javaquarium.utils;

import be.bnair.javaquarium.Main;
import be.bnair.javaquarium.models.Sexe;
import be.bnair.javaquarium.models.Sexualite;
import be.bnair.javaquarium.models.enfants.Carnivores;
import be.bnair.javaquarium.models.enfants.Herbivores;
import be.bnair.javaquarium.models.parents.Algue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.text.resources.cldr.shi.FormatData_shi;

import java.io.*;
import java.util.Random;

public class FileUtils
{
    private Gson gson;

    public FileUtils() {
        this.gson = this.createGsonInstance();
    }

    private Gson createGsonInstance() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }

    public String serializeObject(final Object player) {
        return this.gson.toJson(player);
    }

    public Object deserializeObject(final String json, final Class<?> object) {
        return this.gson.fromJson(json, (Class)object);
    }

    public void createFile(final File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    public void save(final File file, final String text) {
        try {
            this.createFile(file);
            final FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadContent(final File file) {
        if (file.exists()) {
            try {
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                final StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }
                reader.close();
                return text.toString();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void readConfigFileLineByLine() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("config.txt"));
            String line = reader.readLine();
            while (line != null && line.length() > 0) {
                String[] s = line.split(" ");
                switch (s[1]) {
                    case "Algue":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterAlgue(new Algue(Integer.parseInt(s[2].replace("ans", ""))));
                        }
                        break;
                    case "Thon":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterPoisson(new Carnivores(s[2], FishUtils.getRandomSexe(), Carnivores.Race.Thon, FishUtils.getRandomSexualite(), Integer.parseInt(s[3].replace("ans", ""))));
                        }
                        break;
                    case "Mérou":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterPoisson(new Carnivores(s[2], FishUtils.getRandomSexe(), Carnivores.Race.Mérou, FishUtils.getRandomSexualite(), Integer.parseInt(s[3].replace("ans", ""))));
                        }
                        break;
                    case "PoissonClown":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterPoisson(new Carnivores(s[2], FishUtils.getRandomSexe(), Carnivores.Race.PoissonClown, FishUtils.getRandomSexualite(), Integer.parseInt(s[3].replace("ans", ""))));
                        }
                        break;


                    case "Sole":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterPoisson(new Herbivores(s[2], FishUtils.getRandomSexe(), Herbivores.Race.Sole, FishUtils.getRandomSexualite(), Integer.parseInt(s[3].replace("ans", ""))));
                        }
                        break;
                    case "Bar":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterPoisson(new Herbivores(s[2], FishUtils.getRandomSexe(), Herbivores.Race.Bar, FishUtils.getRandomSexualite(), Integer.parseInt(s[3].replace("ans", ""))));
                        }
                        break;
                    case "Carpe":
                        for(int i = 0; i < Integer.parseInt(s[0]); i++)
                        {
                            Main.aquarium.AjouterPoisson(new Herbivores(s[2], FishUtils.getRandomSexe(), Herbivores.Race.Carpe, FishUtils.getRandomSexualite(), Integer.parseInt(s[3].replace("ans", ""))));
                        }
                        break;
                    default:
                        System.out.println("Erreur, type de Poisson/Algue invalide (" + s[1] + ")");
                        break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}