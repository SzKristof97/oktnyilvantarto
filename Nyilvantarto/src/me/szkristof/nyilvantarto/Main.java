package me.szkristof.nyilvantarto;

import java.util.Date;
import java.util.List;

import me.szkristof.nyilvantarto.enums.*;
import me.szkristof.nyilvantarto.managers.*;
import me.szkristof.nyilvantarto.models.*;

/**
 * A nyilvantartó alkalmazás kiindulási pontja.
 */
public class Main {

    //#region Properties
    private PersonManager personManager;
    private MENU currentMenu;
    //#endregion

    /**
     * A nyilvantartó alkalmazás indítása.
     *
     * @param args A program paraméterei
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * A nyilvantartó alkalmazás indítása.
     */
    private void run() {
        personManager = new PersonManager();
        currentMenu = MENU.MAINMENU;

        boolean isRunning = true;

        while (isRunning) {
            switch (currentMenu) {
                case MAINMENU:
                    currentMenu = MainMenu();
                    break;
                case PERSONMANAGERMENU:
                    currentMenu = PersonManagerMenu();
                    break;
                case PERSONADDINGMENU:
                    currentMenu = PersonAddingMenu();
                    break;
                case PERSONREMOVINGMENU:
                    currentMenu = PersonRemovingMenu();
                    break;
                case PERSONSEARCHINGMENU:
                    currentMenu = PersonSearchingMenu();
                    break;
                case PERSONEDITINGMENU:
                    currentMenu = PersonEditingMenu();
                    break;
                case PERSONLISTINGMENU:
                    currentMenu = PersonListingMenu();
                    break;
                default:
                case EXIT:
                    isRunning = false;
                    break;
            }
        }
    }

    //#region Menu methods

    /**
     * Főmenü 
     * @return A következő menü
     */
    private MENU MainMenu(){
        ConsoleManager.WriteMessage("===== [ Fő Menü ] =====\n");
        ConsoleManager.WriteMessage("1. Személyek kezelése\n");
        ConsoleManager.WriteMessage("2. Kilépés\n");

        int menuChoice = ConsoleManager.ReadInt("Válassz menüpontot: ");

        switch (menuChoice) {
            case 1:
                return MENU.PERSONMANAGERMENU;
            case 2:
                return MENU.EXIT;
            default:
                ConsoleManager.WriteMessage("Hibás menüpont!");
                return MENU.MAINMENU;
        }
    }

    /**
     * Személyek kezelése menü
     * @return A következő menü
     */
    private MENU PersonManagerMenu(){
        ConsoleManager.WriteMessage("===== [ Személyek kezelése ] =====\n");
        ConsoleManager.WriteMessage("1. Személy hozzáadása\n");
        ConsoleManager.WriteMessage("2. Személy törlése\n");
        ConsoleManager.WriteMessage("3. Személy keresése\n");
        ConsoleManager.WriteMessage("4. Személy szerkesztése\n");
        ConsoleManager.WriteMessage("5. Személyek listázása\n");
        ConsoleManager.WriteMessage("6. Vissza a főmenübe\n");

        int menuChoice = ConsoleManager.ReadInt("Válassz menüpontot: ");

        switch (menuChoice) {
            case 1:
                return MENU.PERSONADDINGMENU;
            case 2:
                return MENU.PERSONREMOVINGMENU;
            case 3:
                return MENU.PERSONSEARCHINGMENU;
            case 4:
                return MENU.PERSONEDITINGMENU;
            case 5:
                return MENU.PERSONLISTINGMENU;
            case 6:
                return MENU.MAINMENU;
            default:
                return MENU.PERSONMANAGERMENU;
        }
    }

    /**
     * Személy hozzáadása menü
     * @return A következő menü
     */
    private MENU PersonAddingMenu(){
        ConsoleManager.WriteMessage("===== [ Személy hozzáadása ] =====\n");
        ConsoleManager.WriteMessage("1. Diák\n");
        ConsoleManager.WriteMessage("2. Dolgozó\n");

        int menuChoice = ConsoleManager.ReadInt("Válassz menüpontot: ");
        ConsoleManager.Clear();

        switch(menuChoice){
            case 1:
                AddingStudent();
                return MENU.PERSONMANAGERMENU;
            case 2:
                AddingWorker();
                return MENU.PERSONMANAGERMENU;
            default:
                return MENU.PERSONMANAGERMENU;
        }
    }

    /**
     * Dolgozó hozzáadása
     */
    private void AddingWorker(){
        ConsoleManager.Clear();
        ConsoleManager.WriteMessage("===== [ Dolgozó hozzáadása ] =====\n");
        String name = ConsoleManager.ReadString("Adja meg a dolgozó nevét: ");
        if (name.isEmpty() || name == null) return;

        int age = ConsoleManager.ReadInt("Adja meg a dolgozó életkorát: ");
        if (age == -1) return;
        
        ConsoleManager.WriteMessage("\n===== [ Beosztások ] =====\n");
        for (int i = 0; i < WORKERPOST.values().length; i++) {
            ConsoleManager.WriteMessage((i + 1) + ") " + WORKERPOST.values()[i].toString() + " - Bér: " + WORKERPOST.values()[i].getSalary() + " Ft");
        }

        int postChoice = ConsoleManager.ReadInt("\nVálassz beosztást: ");
        if (postChoice == -1) return;
        
        boolean isAdded = personManager.AddWorker(new Worker(name, age, new Date(System.currentTimeMillis()), false, WORKERPOST.values()[postChoice - 1]));
        
        if (isAdded) {
            ConsoleManager.WriteMessage("\nSikeres hozzáadás!");
        } else {
            ConsoleManager.WriteMessage("\nHiba történt!");
        }
        ConsoleManager.WriteMessage("\nNyomjon ENTER-t a folytatáshoz!");
    }

    /**
     * Diák hozzáadása
     */
    private void AddingStudent(){
        ConsoleManager.Clear();
        ConsoleManager.WriteMessage("===== [ Diák hozzáadása ] =====\n");
        String name = ConsoleManager.ReadString("Adja meg a diák nevét: ");
        if (name.isEmpty() || name == null) return;

        int age = ConsoleManager.ReadInt("Adja meg a diák életkorát: ");
        if (age == -1) return;
        
        String className = ConsoleManager.ReadString("Adja meg a diák osztályát: ");
        if (className.isEmpty() || className == null) return;

        boolean isAdded = personManager.AddStudent(new Student(name, age, new Date(System.currentTimeMillis()), false, className));
        if (isAdded) {
            ConsoleManager.WriteMessage("\nSikeres hozzáadás!");
        } else {
            ConsoleManager.WriteMessage("\nHiba történt!");
        }
        ConsoleManager.WriteMessage("\nNyomjon ENTER-t a folytatáshoz!");
    }
    
    /**
     * Személy törlése menü
     * @return A következő menü
     */
    private MENU PersonRemovingMenu() {
        ConsoleManager.WriteMessage("===== [ Személy törlése ] =====\n");
        String name = ConsoleManager.ReadString("Adja meg a személy nevét: ");
        if (name.isEmpty() || name == null) return MENU.PERSONMANAGERMENU;

        boolean isRemoved = personManager.RemovePerson(name);

        if (isRemoved) {
            ConsoleManager.WriteMessage("Sikeres törlés!");
        } else {
            ConsoleManager.WriteMessage("Nem sikerült törölni a személyt!");
        }
        ConsoleManager.ReadString("\nNyomj ENTER-t a folytatáshoz...");

        return MENU.PERSONMANAGERMENU;
    }
    
    /**
     * Személy keresése menü
     * @return A következő menü
     */
    private MENU PersonSearchingMenu(){
        ConsoleManager.WriteMessage("===== [ Személy keresése ] =====\n");
        String name = ConsoleManager.ReadString("Adja meg a személy nevét vagy annak egy részét: ");
        if (name.isEmpty() || name == null) return MENU.PERSONMANAGERMENU;

        List<Person> person = personManager.SearchPerson(name);
        if (person.size() == 0) {
            ConsoleManager.WriteMessage("Nincs találat!");
        } else {
            ConsoleManager.Clear();
            ConsoleManager.WriteMessage("\n===== [ Találatok ] =====\n");
            for (Person p : person) {
                ConsoleManager.WriteMessage(p.toString());
            }
        }

        ConsoleManager.WriteMessage("\nNyomjon ENTER-t a folytatáshoz...");
        return MENU.PERSONMANAGERMENU;
    }
    
    /**
     * Személyek listázása menü
     * @return A következő menü
     */
    public MENU PersonListingMenu(){
        ConsoleManager.WriteMessage("===== [ Személyek listázása ] =====\n");
        List<Worker> workers = personManager.GetWorkers();
        List<Student> students = personManager.GetStudents();

        if (workers.size() == 0 && students.size() == 0) {
            ConsoleManager.WriteMessage("Nincs személy!");
        } else {
            ConsoleManager.Clear();
            ConsoleManager.WriteMessage("\n===== [ Dolgozók ] =====\n");
            for (Worker w : workers) {
                ConsoleManager.WriteMessage(w.toString());
            }
            ConsoleManager.WriteMessage("\n===== [ Diákok ] =====\n");
            for (Student s : students) {
                ConsoleManager.WriteMessage(s.toString());
            }
        }

        ConsoleManager.WriteMessage("\nNyomjon ENTER-t a folytatáshoz...");
        return MENU.PERSONMANAGERMENU;
    }
    
    /**
     * Személy szerkesztése menü
     * @return A következő menü
     */
    private MENU PersonEditingMenu(){
        ConsoleManager.WriteMessage("===== [ Személy szerkesztése ] =====\n");
        String name = ConsoleManager.ReadString("Adja meg a személy nevét: ");
        if (name.isEmpty() || name == null) return MENU.PERSONMANAGERMENU;

        // Check the person is a worker or a student
        boolean isWorker = personManager.IsWorker(name);
        boolean isStudent = personManager.IsStudent(name);

        if (!isWorker && !isStudent) {
            ConsoleManager.WriteMessage("Nincs ilyen személy!");
            return MENU.PERSONMANAGERMENU;
        }else if(isWorker && !isStudent){
            return EditWorker(name);
        }else if(!isWorker && isStudent){
            return EditStudent(name);
        }else {
            ConsoleManager.WriteMessage("Nem sikerült szerkeszteni a személyt!");
        }

        ConsoleManager.WriteMessage("\nNyomjon ENTER-t a folytatáshoz...");
        return MENU.PERSONMANAGERMENU;
    }

    /**
     * Dolgozó szerkesztése
     * @param name A szerkesztendő dolgozó neve
     * @return A következő menü
     */
    private MENU EditWorker(String name){
        ConsoleManager.WriteMessage("\n===== [ Dolgozó szerkesztése ] =====\n");
        Worker worker = personManager.GetWorker(name);

        String newName = ConsoleManager.ReadString("Adja meg az új nevet (vagy hadja üresen): ");
        if (newName.isEmpty() || newName == null) newName = name;

        int newAge = ConsoleManager.ReadInt("Adja meg az új életkorát (vagy hadja üresen): ");
        if (newAge == 0) newAge = worker.getAge();

        boolean isFired = ConsoleManager.ReadBoolean("Ki lett rúgva? (I/N): ");

        ConsoleManager.WriteMessage("\n===== [ Beosztások ] =====\n");
        for (int i = 0; i < WORKERPOST.values().length; i++) {
            ConsoleManager.WriteMessage((i + 1) + ") " + WORKERPOST.values()[i].toString() + " - Bér: " + WORKERPOST.values()[i].getSalary() + " Ft");
        }

        int postChoice = ConsoleManager.ReadInt("\nVálassz beosztást(vagy hadja üresen): ");
        
        Worker newWorker = new Worker(name, newAge, worker.getHiredDate(), isFired, (postChoice > 0 ? WORKERPOST.values()[postChoice - 1]:worker.getPost()));

        boolean isEdited = personManager.EditWorker(worker, newWorker);

        if (isEdited) {
            ConsoleManager.WriteMessage("Sikeres szerkesztés!");
        } else {
            ConsoleManager.WriteMessage("Nem sikerült szerkeszteni a személyt!");
        }

        ConsoleManager.ReadString("\nNyomj ENTER-t a folytatáshoz...");
        return MENU.PERSONMANAGERMENU;
    }

    /**
     * Diák szerkesztése
     * @param name A szerkesztendő diák neve
     * @return A következő menü
     */
    private MENU EditStudent(String name){
        ConsoleManager.WriteMessage("\n===== [ Diák szerkesztése ] =====\n");
        Student student = personManager.GetStudent(name);

        String newName = ConsoleManager.ReadString("Adja meg az új nevet (vagy hadja üresen): ");
        if (newName.isEmpty() || newName == null) newName = name;

        int newAge = ConsoleManager.ReadInt("Adja meg az új életkorát (vagy hadja üresen): ");
        if (newAge == 0) newAge = student.getAge();

        String newClassName = ConsoleManager.ReadString("Adja meg az új osztály nevét (vagy hadja üresen): ");
        if (newClassName.isEmpty() || newClassName == null) newClassName = student.getClassName();

        boolean isFired = ConsoleManager.ReadBoolean("Ki lett rúgva? (I/N): ");

        Student newStudent = new Student(name, newAge, student.getHiredDate(), isFired, newClassName);

        boolean isEdited = personManager.EditStudent(student, newStudent);

        if (isEdited) {
            ConsoleManager.WriteMessage("Sikeres szerkesztés!");
        } else {
            ConsoleManager.WriteMessage("Nem sikerült szerkeszteni a személyt!");
        }

        ConsoleManager.ReadString("\nNyomj ENTER-t a folytatáshoz...");
        return MENU.PERSONMANAGERMENU;
    }
    //#endregion
}