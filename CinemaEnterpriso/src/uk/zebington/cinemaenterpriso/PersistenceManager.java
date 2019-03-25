package uk.zebington.cinemaenterpriso;

import uk.zebington.cinemaenterpriso.entities.singletons.Persistable;

import java.io.*;

/**
 * @author Aspen Thompson
 */
public class PersistenceManager {
    public static Persistable loadInstance(String path) {
        try {
            System.out.println("Reading from " + path);
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Persistable instance = (Persistable) in.readObject();
            in.close();
            fileIn.close();
            return instance;
        } catch (IOException e) {
            System.out.println(path + " not found.");
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeInstance(Persistable instance) {
        try {
            String path = instance.getSavePath();
            System.out.println("Writing to " + path);
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.close();
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
