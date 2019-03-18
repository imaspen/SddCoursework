package uk.zebington.cinemaenterpriso.entities;

import java.io.*;

/**
 * @author Aspen Thompson
 */
class PersistenceManager {
    static <T> T loadInstance(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            T instance = (T) in.readObject();
            in.close();
            fileIn.close();
            return instance;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void writeInstance(Serializable instance, String path) {
        try {
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
