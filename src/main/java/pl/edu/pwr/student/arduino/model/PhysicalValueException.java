package pl.edu.pwr.student.arduino.model;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 20.10.2020

    Plik: PhysicalValueException.java

 */

public class PhysicalValueException extends Exception {
    public PhysicalValueException(String message) {
        super("Could not convert value to a physical value. " + message);
    }
}