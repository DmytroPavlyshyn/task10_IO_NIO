package com.pavlyshyn.task1;

import java.io.Serializable;
import java.util.Objects;

public class Droid implements Serializable {
    String name;
    int speed;
    int power;
    transient Weapon weapon;

    public Droid(String name, int speed, int power, Weapon weapon) {
        this.name = name;
        this.speed = speed;
        this.power = power;
        this.weapon = weapon;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Droid)) return false;
        Droid droid = (Droid) o;
        return getSpeed() == droid.getSpeed() &&
                getPower() == droid.getPower() &&
                Objects.equals(getName(), droid.getName()) &&
                Objects.equals(getWeapon(), droid.getWeapon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSpeed(), getPower(), getWeapon());
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", power=" + power +
                ", weapon=" + weapon +
                '}';
    }
}
