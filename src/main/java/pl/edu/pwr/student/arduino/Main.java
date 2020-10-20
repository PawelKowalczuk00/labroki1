package pl.edu.pwr.student.arduino;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 20.10.2020

    Plik: Main.java

 */

import pl.edu.pwr.student.arduino.app.console.ConsoleCRUDApplication;

public class Main {

    private static final String signature = "Author: Paweł Kowalczuk"+
            System.lineSeparator()+ "Date: 12.10.2020"+
            System.lineSeparator()+ "Program: laboratoria 1 - Arduino" + System.lineSeparator();

    public static void main(String[] args) {
        System.out.println(signature);
        ConsoleCRUDApplication app = new ConsoleCRUDApplication();
        app.start();
    }
}
