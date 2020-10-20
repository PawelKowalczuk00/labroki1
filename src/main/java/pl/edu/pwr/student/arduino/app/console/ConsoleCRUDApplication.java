package pl.edu.pwr.student.arduino.app.console;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 12.10.2020 na zajęciach

    Plik: ConsoleCRUDApplication.java

 */

import pl.edu.pwr.student.arduino.app.Application;
import pl.edu.pwr.student.arduino.model.ArduinoException;
import pl.edu.pwr.student.arduino.model.ArduinoModel;
import pl.edu.pwr.student.arduino.model.PhysicalValue;
import pl.edu.pwr.student.arduino.model.PhysicalValueException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleCRUDApplication extends Application {

    protected final String GREETING = "Welcome to the arduino entities creator app." + System.lineSeparator(),
            INITIAL_PROMPT = "What do you want to do now? (<?> - help)";
    protected Boolean stop;
    protected Scanner scanner = new Scanner(System.in);

    public ConsoleCRUDApplication() {
        this.stop = false;
    }

    @Override
    public void start() {
        System.out.println(GREETING);
        String userInput;
        System.out.println("***!!!*** Sometimes you have to omit first line. ***!!!***");
        while (!stop) {
            try {
                userInput = prompt(INITIAL_PROMPT);
                if (userInput.equals("?")) {
                    showHelp();
                } else if (userInput.equals("0")) { // zamknij aplikację
                    stop = true;
                } else if (userInput.equals("1")) { // tworzenie nowego
                    createPath();
                } else if (userInput.equals("2")) { // wczytanie istniejącego
                    readPath();
                } else if (userInput.equals("3")) { // edycja istniejącego
                    editPath();
                } else if (userInput.equals("4")) { // usunięcie istniejącego
                    deletePath();
                } else
                    System.out.println("Could not recognize the instruction. Try again.");
            } catch (UnexpectedException ex) {
                System.err.println(ex.getMessage());
                System.err.println("Shutting down the application due to error above.");
                stop = true;
            } catch (IOException | ArduinoException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    protected void createPath() throws ArduinoException, IOException {
        ArduinoModel arduino = getArduinoFromUser();
        crud.create(arduino, arduino.getName());
        System.out.println("Done!");
    }

    protected void readPath() throws IOException {
        System.out.println("Which file do you want to view?");
        showLibraryContent();
        System.out.println(crud.read(promptInline("Name: ")));
    }

    protected void editPath() throws ArduinoException, IOException {
        System.out.println("Which file do you want to edit?");
        showLibraryContent();
        ArduinoModel oldArduino = crud.read(promptInline("Name: "));
        System.out.println(oldArduino);
        ArduinoModel newArduino = getArduinoFromUser();
        crud.update(newArduino, oldArduino.getName());
        System.out.println("Done!");
    }

    protected void deletePath() throws IOException {
        System.out.println("Which file do you want to delete?");
        showLibraryContent();
        crud.delete(promptInline("Name: "));
        System.out.println("Done!");
    }

    protected String prompt(String info) {
        System.out.println(info);
        String userInput = scanner.nextLine();
        return userInput.trim();
    }

    protected String promptInline(String info) {
        System.out.print(info + "\t");
        String userInput = scanner.nextLine();
        return userInput.trim();
    }

    protected int promptInlineInt(String info) {
        while (true) {
            try {
                return Integer.parseInt(promptInline(info));
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    protected PhysicalValue promptInlinePhysicalValue(String info) {
        while (true) {
            try {
                return new PhysicalValue(promptInline(info));
            } catch (PhysicalValueException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    protected ArduinoModel getArduinoFromUser() throws ArduinoException {
        return new ArduinoModel(
                promptInline("Name:"),
                promptInlineInt("Number of pins:"),
                promptInline("Microcontroller:"),
                promptInlinePhysicalValue("Memory:"),
                promptInlinePhysicalValue("Clock speed:")
        );

    }

    protected void showHelp() {
        System.out.println("0 - close" + System.lineSeparator() +
                "1 - create and to library new element" + System.lineSeparator() +
                "2 - show element existing in library" + System.lineSeparator() +
                "3 - edit element from library" + System.lineSeparator() +
                "4 - delete element from library" + System.lineSeparator() +
                "? - help");
    }

    protected void showLibraryContent() throws IOException {
        System.out.println(Arrays.toString(crud.listResources()));
    }
}
