package pl.edu.pwr.student.arduino.model;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 12.10.2020 na zajęciach

    Plik: ArduinoModel.java

 */

public class ArduinoModel {

    private int pins;
    private String name, microcontroller;
    private PhysicalValue memory, clock;

    public ArduinoModel() {
    }

    public ArduinoModel(String name, int pins, String microcontroller, PhysicalValue memory, PhysicalValue clock) throws ArduinoException {
        setName(name);
        setPins(pins);
        setMicrocontroller(microcontroller);
        setMemory(memory);
        setClock(clock);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ArduinoException {
        if (stringEmpy(name))
            throw new ArduinoException("Name not specified");
        else if (name.contains(" "))
            throw new ArduinoException("White spaces not allowedin name - <"+name+">");
        this.name = name.trim();
    }

    public int getPins() {
        return pins;
    }

    public void setPins(int pins) throws ArduinoException {
        if (pins < 1)
            throw new ArduinoException("To few pins - <"+pins+">");
        this.pins = pins;
    }

    public PhysicalValue getMemory() {
        return memory;
    }

    public void setMemory(PhysicalValue memory) {
        this.memory = memory;
    }

    public PhysicalValue getClock() {
        return clock;
    }

    public void setClock(PhysicalValue clock) {
        this.clock = clock;
    }

    public String getMicrocontroller() {
        return microcontroller;
    }

    public void setMicrocontroller(String microcontroller) throws ArduinoException {
        if (stringEmpy(microcontroller))
            throw new ArduinoException("Microcontroller not specified");
        this.microcontroller = microcontroller.trim();
    }

    private boolean stringEmpy(String string) {
        return string == null || string.trim().equals("");
    }

    @Override
    public String toString() {
        return "Name: " + name + System.lineSeparator() +
                "Pins: " + pins + System.lineSeparator() +
                "Microcontroller: " + microcontroller + System.lineSeparator() +
                "Memory: " + memory + System.lineSeparator() +
                "Clock: " + clock;
    }
}
