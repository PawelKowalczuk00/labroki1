package pl.edu.pwr.student.arduino.app;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 20.10.2020

    Plik: CRUD.java

 */

import java.io.IOException;

public interface CRUD <T> {
    void create (T arduino, String fileName) throws IOException;

    T read (String fileName) throws IOException;

    void update (T newObject, String oldFileName) throws IOException;

    void delete (String fileName) throws IOException;

    String[] listResources() throws IOException;
}
