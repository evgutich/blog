package com.leverx.farmdog.animals;

public class Dog {

    private int age;
    private int satiety;
    private int experience;
    private boolean isHealthy;

    public Dog(int age, int satiety, int experience, boolean isHealthy) {
        this.age = age;
        this.satiety = satiety;
        this.experience = experience;
        this.isHealthy = isHealthy;
    }

    public int getAge() {
        return age;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public boolean isWellFed() {
        return satiety > 70;
    }

    public boolean isTrained() {
        return experience > 100;
    }

    public void doWork(Work work) {
        setSatiety(getSatiety() - work.getEnergy());
        setExperience(getExperience() + 10);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", satiety=" + satiety +
                ", experience=" + experience +
                ", isHealthy=" + isHealthy +
                '}';
    }
}
