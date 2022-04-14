package me.szkristof.nyilvantarto.models;

import java.util.Date;

public class Person implements Comparable<Person> {
    //#region Properties
    protected String name;
    protected int age;
    protected Date hiredDate;
    protected boolean isFired;
    //#endregion

    //#region Constructors
    /**
     * A {@link Person} osztály konstruktora.
     *
     * @param name A {@link Person} neve
     * @param age A {@link Person} életkora
     * @param hiredDate A {@link Person} felvételének dátuma
     * @param isFired A {@link Person} ki lett-e rúgva
     */
    public Person(String name, int age, Date hiredDate, boolean isFired) {
        this.name = name;
        this.age = age;
        this.hiredDate = hiredDate;
        this.isFired = isFired;
    }
    //#endregion

    //#region Getters

    /**
     * A {@link Person} nevének lekérdezése.
     *
     * @return A {@link Person} neve
     */
    public String getName() {
        return name;
    }

    /**
     * A {@link Person} életkorának lekérdezése.
     *
     * @return A {@link Person} életkora
     */
    public int getAge() {
        return age;
    }

    /**
     * A {@link Person} felvételének dátumának lekérdezése.
     *
     * @return A {@link Person} felvételének dátuma
     */
    public Date getHiredDate() {
        return hiredDate;
    }

    /**
     * A {@link Person} jelenleg itt dolgozik-e vagy már nem.
     *
     * @return A {@link Person} ki lett-e rúgva
     */
    public boolean isFired() {
        return isFired;
    }

    //#endregion

    //#region Setters
    /**
     * A {@link Person} ki lett-e rúgva beállítása.
     *
     * @param isFired A {@link Person} ki lett-e rúgva
     */
    public void setFired(boolean isFired) {
        this.isFired = isFired;
    }

    /**
     * A {@link Person} nevének beállítása.
     *
     * @param name A {@link Person} neve
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A {@link Person} életkorának beállítása.
     *
     * @param age A {@link Person} életkora
     */
    public void setAge(int age) {
        this.age = age;
    }
    //#endregion

    //#region Override methods
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hiredDate=" + hiredDate +
                ", isFired=" + isFired +
                '}';
    }

    /**
     * A {@link Person} osztály egyik {@link Comparable} interfész implementációjának
     * megvalósítása. Összehasonlítja a megadott {@link Person} objektum nevét a
     * jelenlegi {@link Person} objektum nevével.
     * 
     * @param o A másik {@link Person} objektum, amivel összehasonlítjuk a nevét.
     */
    @Override
    public int compareTo(Person o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }
    //#endregion

    
}
