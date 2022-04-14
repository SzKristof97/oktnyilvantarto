package me.szkristof.nyilvantarto.managers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import me.szkristof.nyilvantarto.enums.WORKERPOST;
import me.szkristof.nyilvantarto.models.*;

/**
 * Reads and writes the data to the xml file.
 */
public class XmlManager {

    //#region Create the xml file and write the data to it.

    /**
     * Creates the xml file and writes the data to it.
     * @param fileName The name of the xml file.
     * @param workers The list of workers.
     * @param students The list of students.
     * @return True if the file was created, false if not.
     */
    public static boolean WriteDataToXml(String fileName, List<Worker> workers, List<Student> students) {
        Document doc;
        Element e = null;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            // Create the main root (person) element
            Element persons = doc.createElement("Persons");

            // Create the workers and add it to root
            Element workerElement = doc.createElement("Workers");
            for (Worker worker : workers){
                e = doc.createElement("Worker");

                e.setAttribute("name", worker.getName());
                e.setAttribute("age", String.valueOf(worker.getAge()));
                e.setAttribute("hiredDate", String.valueOf(worker.getHiredDate().getTime()));
                e.setAttribute("isFired", String.valueOf(worker.isFired()));
                e.setAttribute("post", String.valueOf(worker.getPost()));
                workerElement.appendChild(e);
            }
            persons.appendChild(workerElement);

            // Create the stundents and add it to root
            Element studentElement = doc.createElement("Students");

            for (Student student : students){
                e = doc.createElement("Student");

                e.setAttribute("name", student.getName());
                e.setAttribute("age", String.valueOf(student.getAge()));
                e.setAttribute("hiredDate", String.valueOf(student.getHiredDate().getTime()));
                e.setAttribute("isFired", String.valueOf(student.isFired()));
                e.setAttribute("className", String.valueOf(student.getClassName()));
                studentElement.appendChild(e);
            }
            persons.appendChild(studentElement);
            doc.appendChild(persons);

            // Save the XML
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            tr.transform(new DOMSource(doc), new StreamResult(fileName));
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    //#endregion

    //#region Read the data from the xml file.
    /**
     * Reads the worker data from the xml file.
     * @param fileName The name of the xml file.
     * @return The list of workers.
     */
    public static List<Worker> ReadWorkerDataFromXml(String fileName){
        List<Worker> workers = new java.util.ArrayList<Worker>();
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fileName);

            // Get the root element
            Element root = doc.getDocumentElement();

            // Get the workers
            NodeList workersList = root.getElementsByTagName("Worker");
            for (int i = 0; i < workersList.getLength(); i++){
                Element workerElement = (Element) workersList.item(i);
                Worker worker = new Worker(
                    workerElement.getAttribute("name"),
                    Integer.parseInt(workerElement.getAttribute("age")),
                    new Date(Long.parseLong(workerElement.getAttribute("hiredDate"))),
                    Boolean.parseBoolean(workerElement.getAttribute("isFired")),
                    WORKERPOST.parse(workerElement.getAttribute("post"))
                );
                workers.add(worker);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return workers;
    }

    /**
     * Reads the student data from the xml file.
     * @param fileName The name of the xml file.
     * @return The list of students.
     */
    public static List<Student> ReadStudentDataFromXml(String fileName){
        List<Student> students = new java.util.ArrayList<Student>();
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(fileName);

            // Get the root element
            Element root = doc.getDocumentElement();

            // Get the students
            NodeList studentsList = root.getElementsByTagName("Student");
            for (int i = 0; i < studentsList.getLength(); i++){
                Element studentElement = (Element) studentsList.item(i);
                Student student = new Student(
                    studentElement.getAttribute("name"),
                    Integer.parseInt(studentElement.getAttribute("age")),
                    new Date(Long.parseLong(studentElement.getAttribute("hiredDate"))),
                    Boolean.parseBoolean(studentElement.getAttribute("isFired")),
                    studentElement.getAttribute("className")
                );
                students.add(student);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return students;
    }
    //#endregion
}
