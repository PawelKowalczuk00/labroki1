package pl.edu.pwr.student.arduino.app;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 12.10.2020 na zajęciach

    Plik: Application.java

 */

import pl.edu.pwr.student.arduino.model.ArduinoModel;

public abstract class Application {

    protected final CRUD<ArduinoModel> crud = new ArduinoCRUD();

    public abstract void start();
}
