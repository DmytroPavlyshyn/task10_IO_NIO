package com.pavlyshyn.task1;

import java.io.Serializable;
import java.util.Set;

public class SpaceShip implements Serializable {
    String name;
    int speed;
    int numberOfGuns;
    Set<Droid> droidSet;

    public SpaceShip(String name, int speed, int numberOfGuns, Set<Droid> droidSet) {
        this.name = name;
        this.speed = speed;
        this.numberOfGuns = numberOfGuns;
        this.droidSet = droidSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public void setNumberOfGuns(int numberOfGuns) {
        this.numberOfGuns = numberOfGuns;
    }

    public Set<Droid> getDroidSet() {
        return droidSet;
    }

    public void setDroidSet(Set<Droid> droidSet) {
        this.droidSet = droidSet;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", numberOfGuns=" + numberOfGuns +
                ", droidSet=" + droidSet +
                '}';
    }
}
