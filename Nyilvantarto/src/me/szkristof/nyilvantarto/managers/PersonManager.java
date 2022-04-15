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

        LoadDatas();
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
        if (workers.stream().anyMatch(w -> w.getName().toLowerCase().equals(worker.getName().toLowerCase()))) {
            return false;
        }
        return workers.add(worker);
    }

    /**
     * A {@link PersonManager} osztály {@link #workers} lástájából törli a megadott {@link Worker}-t.
     * 
     * @param worker A törlendő {@link Worker}
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean RemoveWorker(Worker worker) {
        return RemoveWorker(worker.getName());
    }

    /**
     * A {@link PersonManager} osztály {@link #workers} lástájából törli a megadott {@link Worker} nevű elemet.
     * 
     * @param name A törlendő {@link Worker} neve
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean RemoveWorker(String name) {
        Worker foundedW = null;
        for (Worker worker : workers) {
            if (worker.getName().toLowerCase().equals(name.toLowerCase())) {
                foundedW = worker;
                break;
            }
        }
        return foundedW == null ? false : workers.remove(foundedW);
    }

    /**
     * A {@link PersonManager} osztály {@link #students} lástájához hozzáad egy új {@link Student}-t.
     * 
     * @param student A hozzáadandó {@link Student}
     * @return igaz ha sikeresen hozzáadtuk, hamis ha nem!
     */
    public boolean AddStudent(Student student) {
        if (students.stream().anyMatch(w -> w.getName().toLowerCase().equals(student.getName().toLowerCase()))) {
            return false;
        }
        return students.add(student);
    }
    
    /**
     * A {@link PersonManager} osztály {@link #students} lástájából törli a megadott {@link Student}-t.
     * 
     * @param student A törlendő {@link Student}
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean RemoveStudent(Student student) {
        return students.remove(student);
    }
    
    /**
     * A {@link PersonManager} osztály {@link #students} lástájából törli a megadott {@link Student} nevű elemet.
     * 
     * @param name A törlendő {@link Student} neve
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean RemoveStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return students.remove(student);
            }
        }
        return false;
    }

    /**
     * A {@link PersonManager} osztály {@link #workers} és {@link #students} lástájából törli a megadott {@link Person}-t.
     * @param name
     * @return
     */
    public boolean RemovePerson(String name) {
        for (Worker worker : workers) {
            if (worker.getName().equals(name)) {
                return workers.remove(worker);
            }
        }
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return students.remove(student);
            }
        }
        return false;
    }


    /**
     * A {@link PersonManager} osztály {@link #workers} lástáját adja vissza.
     * 
     * @return {@link #workers} lástája
     */
    public List<Worker> GetWorkers() {
        return workers;
    }

    /**
     * A {@link PersonManager} osztály {@link #students} lástáját adja vissza.
     * 
     * @return {@link #students} lástája
     */
    public List<Student> GetStudents() {
        return students;
    }
    
    /**
     * A {@link PersonManager} osztály {@link #workers} és {@link #students} lástájából adja vissza a megadott {@link Person} nevű elemet.
     * @param name A keresett {@link Person} neve
     * @return A megadott {@link Person}-ek listája
     */
    public List<Person> SearchPerson(String name){
        List<Person> result = new ArrayList<Person>();
        for (Worker worker : workers) {
            if (worker.getName().contains(name)) {
                result.add(worker);
            }
        }
        for (Student student : students) {
            if (student.getName().contains(name)) {
                result.add(student);
            }
        }
        return result;
    }
    
    /**
     * A {@link PersonManager} osztály {@link #workers} listából kikeresi a megadott {@link Worker} nevű elemet,
     * és ha talál akkor a {@link Person} egy {@link Worker}
     * @param name A keresett {@link Worker} neve
     * @return igaz ha talált, hamis ha nem!
     */
    public boolean IsWorker(String name) {
        return GetWorker(name) != null;
    }

    /**
     * A {@link PersonManager} osztály {@link #students} listából kikeresi a megadott {@link Student} nevű elemet,
     * és ha talál akkor a {@link Person} egy {@link Student}
     * @param name A keresett {@link Student} neve
     * @return igaz ha talált, hamis ha nem!
     */
    public boolean IsStudent(String name) {
        return GetStudent(name) != null;
    }

    /**
     * A {@link PersonManager} osztály {@link #workers} lástájából adja vissza a megadott {@link Worker} nevű elemet.
     * @return A megadott {@link Worker}
     */
    public Worker GetWorker(String name) {
        for (Worker worker : workers) {
            if (worker.getName().equals(name)) {
                return worker;
            }
        }
        return null;
    }
    
    /**
     * A {@link PersonManager} osztály {@link #students} lástájából adja vissza a megadott {@link Student} nevű elemet.
     */
    public Student GetStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * A {@link PersonManager} osztály {@link #workers} lástájából törli a megadott {@link Worker} nevű elemet,
     * és hozzáad egy új {@link Worker}-t.
     * @param worker A törlendő {@link Worker}
     * @param newWorker A hozzáadandó {@link Worker}
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean EditWorker(Worker worker, Worker newWorker) {
        if (RemoveWorker(worker)) {
            return AddWorker(newWorker);
        }
        return false;
    }
    
    /**
     * A {@link PersonManager} osztály {@link #students} lástájából törli a megadott {@link Student} nevű elemet,
     * és hozzáad egy új {@link Student}-t.
     * @param student A törlendő {@link Student}
     * @param newStudent A hozzáadandó {@link Student}
     * @return igaz ha sikeresen törölte, hamis ha nem!
     */
    public boolean EditStudent(Student student, Student newStudent) {
        if (RemoveStudent(student)) {
            return AddStudent(newStudent);
        }
        return false;
    }
    
    /**
     * A {@link PersonManager} osztály {@link #workers} és {@link #students} lástáját lementi!,
     */
    public boolean Save() {
        return XmlManager.WriteDataToXml("nyilvantarto.xml", workers, students);
    }

    /**
     * A {@link PersonManager} osztály {@link #workers} és {@link #students} lástáját betölti!
     */
    public void LoadDatas(){
        List<Worker> _workers = XmlManager.ReadWorkerDataFromXml("nyilvantarto.xml");
        List<Student> _students = XmlManager.ReadStudentDataFromXml("nyilvantarto.xml");

        _workers.forEach((worker) -> {
            AddWorker(worker);
        });

        _students.forEach((student) -> {
            AddStudent(student);
        });
    }

    //#endregion
}
