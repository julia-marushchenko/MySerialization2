/**
 *  Java program to serialize java object.
 */

package com.myserialization;

import java.io.*;
import java.util.Objects;

/**
 *  Nuts class.
 */
class Nut implements Serializable {
    private String name;
    private transient int weight;

    public Nut(String name, int weight) {
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nut nuts = (Nut) o;
        return weight == nuts.weight && Objects.equals(name, nuts.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "Nut{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}

/**
 *  Main class.
 */
public class Main {

    // JVM entry point.
    public static void main(String[] args) {

        // Creating an object of class Nut.
        Nut nut = new Nut("Peanut", 50);

        // Displaying object before serialization.
        System.out.println(nut); // Output: Nut{name='Peanut', weight=50}

        // Filename where to write an object nut.
        String file = "file.txt";

        // Serializing object nut.
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(nut);
            out.close();
            fos.close();
            System.out.println("Object was serialized");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializing object nut.

        Nut nutDeserialized = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            nutDeserialized = (Nut) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Object was serialized");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Displaying object after serialization.
        System.out.println(nutDeserialized); // Output: Nut{name='Peanut', weight=0}

    }
}