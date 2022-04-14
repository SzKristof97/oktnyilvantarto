package me.szkristof.nyilvantarto.models;

import java.util.Date;

import me.szkristof.nyilvantarto.enums.WORKERPOST;

public class Worker extends Person{

    //#region Properties
    private WORKERPOST post;
    //#endregion

    //#region Constructors

    /**
     * A {@link Worker} osztály konstruktora.
     * @param name A {@link Worker} neve
     * @param age A {@link Worker} életkora
     * @param hiredDate A {@link Worker} felvételének dátuma
     * @param isFired A {@link Worker} ki lett-e rúgva
     * @param post A {@link Worker} munkakör
     */
    public Worker(String name, int age, Date hiredDate, boolean isFired, WORKERPOST post) {
        super(name, age, hiredDate, isFired);
        this.post = post;
    }

    //#endregion
    //#region Getters and Setters

    /**
     * A {@link Worker} munkakörének lekérdezése.
     * @return A {@link Worker} munkakör
     */
    public WORKERPOST getPost() {
        return post;
    }

    /**
     * A {@link Worker} munkakörének beállítása.
     * @param post A {@link Worker} munkakör
     */
    public void setPost(WORKERPOST post) {
        this.post = post;
    }

    //#endregion
    //#region Overrides

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hiredDate=" + hiredDate +
                ", isFired=" + isFired +
                ", post=" + post +
                '}';
    }

    //#endregion
}
