package me.szkristof.nyilvantarto.models;

import java.util.Date;

public class Student extends Person{
    //#region Properties
    private String className;
    //#endregion

    //#region Constructors
    /**
     * A {@link Student} osztály konstruktora.
     *
     * @param name A {@link Student} neve
     * @param age A {@link Student} életkora
     * @param hiredDate A {@link Student} felvételének dátuma
     * @param isFired A {@link Student} ki lett-e rúgva
     * @param className A {@link Student} osztálya
     */
    public Student(String name, int age, Date hiredDate, boolean isFired, String className) {
        super(name, age, hiredDate, isFired);
        this.verboseName = "Tanuló";
        this.className = className;
    }
    //#endregion
    
    //#region Getters and Setters
    /**
     * A {@link Student} osztályának lekérdezése.
     *
     * @return A {@link Student} osztálya
     */
    public String getClassName() {
        return className;
    }

    /**
     * A {@link Student} osztályának beállítása.
     *
     * @param className A {@link Student} osztálya
     */
    public void setClassName(String className) {
        this.className = className;
    }
    //#endregion

    //#region Overrides
    @Override
    public String toString() {
        return super.toString() + ", Osztály: " + className;
    }
    //#endregion
}
