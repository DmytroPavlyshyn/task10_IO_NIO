package com.pavlyshyn.task1;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SpaceShipIO {
    private static final String path = "task1.dat";

    public static SpaceShip read() {
        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))) {
            return (SpaceShip) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(SpaceShip spaceShip) {
        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(spaceShip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Weapon gun121 = new Weapon("Gun121", 500);
        Set<Droid> droids = new HashSet<>();
        droids.add(new Droid("R2-D2", 100, 1000, gun121));
        droids.add(new Droid("C-3PO", 50, 0, null));
        droids.add(new Droid("IG-88", 150, 2000, gun121));
        SpaceShip spaceShip = new SpaceShip("Star Destroyer", 5000, 40, droids);
        System.out.println("Space ship before serialization: ");
        System.out.println(spaceShip);
        System.out.println("Writing...to path task1.dat");
        write(spaceShip);
        System.out.println("Deserialized space ship: ");
        System.out.println(read());
    }
}
