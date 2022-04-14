package me.szkristof.nyilvantarto.managers;

import java.util.ArrayList;
import java.util.List;

import me.szkristof.nyilvantarto.models.*;

public class PersonManager {
    
    //#region Properties

    private List<Worker> workers;
    private List<Student> students;

    //#endregion
    //#region Constructors
    
    /**
     * A {@link PersonManager} osztály konstruktora.
     */
    public PersonManager() {
        workers = new ArrayList<Worker>();
        students = new ArrayList<Student>();
    }

    //#endregion
    //#region Methods
    
    /**
     * A {@link PersonManager} osztály {@link #workers} lástájához hozzáad egy új {@link Worker}-t.
     * 
     * @param worker A hozzáadandó {@link Worker}
     * @return igaz ha sikeresen hozzáadtuk, hamis ha nem!
     */
    public boolean AddWorker(Worker worker) {
        return workers.add(worker);
    }

    /**
     * A {@link PersonManager} osztály {@link #workers} lástájából törli a megadott {@link Worker}-t.
     * 
     * @param worker A törlendő {@link Worker}
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean RemoveWorker(Worker worker) {
        return workers.remove(worker);
    }

    //Remove worker by name
    /**
     * A {@link PersonManager} osztály {@link #workers} lástájából törli a megadott {@link Worker} nevű elemet.
     * 
     * @param name A törlendő {@link Worker} neve
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean RemoveWorker(String name) {
        for (Worker worker : workers) {
            if (worker.getName().equals(name)) {
                return workers.remove(worker);
            }
        }
        return false;
    }
    //#endregion
}
