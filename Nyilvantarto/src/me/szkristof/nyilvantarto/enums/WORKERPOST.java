package me.szkristof.nyilvantarto.enums;

/**
 * Az alkalmazásban használt munkakörök,
 * fizetésekkel együtt.
 */
public enum WORKERPOST {
    PRINCIPAL(450000),
    SECRETARY(350000),
    TEACHER(250000);

    private int salary;

    WORKERPOST(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}