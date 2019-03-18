package uk.zebington.cinemaenterpriso.entities;

import java.io.*;
import java.util.ArrayList;

public class TheaterList extends ArrayList<Theater> {
    private static TheaterList ourInstance;

    public static TheaterList getInstance() {
        if (ourInstance == null) {
            try {
                FileInputStream fileIn = new FileInputStream("TheaterList.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                ourInstance = (TheaterList) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException e) {
                ourInstance = new TheaterList();
                try {
                    FileOutputStream fileOut = new FileOutputStream("TheaterList.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(ourInstance);
                    out.close();
                    fileOut.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ourInstance;
    }

    private TheaterList() {
        super();
//        try {
//            this.add(new Theater(Movie.THE_MATRIX, 150, new Price(1000)));
//            this.add(new Theater(Movie.PAUL_BLART_2, 200, new Price(50)));
//        } catch (NegativePriceException e) {
//            e.printStackTrace();
//        }
    }
}
