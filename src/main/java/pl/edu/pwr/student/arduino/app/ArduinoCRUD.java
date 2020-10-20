package pl.edu.pwr.student.arduino.app;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 20.10.2020

    Plik: ArduinoCRUD.java

 */

import pl.edu.pwr.student.arduino.model.ArduinoModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.UnexpectedException;

class ArduinoCRUD implements CRUD<ArduinoModel> {

    private final ClassLoader resources = this.getClass().getClassLoader();
    private final String ORIENTATION_FILE_NAME = ".classLoaderOrientationFile";
    private final JSONmapper<ArduinoModel> arduinoMapper = new JSONmapper<>();

    @Override
    public void create(ArduinoModel arduino, String fileName) throws IOException  {
        if (arduino == null)
            throw new NullPointerException("Uninitialized arduino can not be saved");
        URL path = resources.getResource(fileName);
        if (path != null)
            throw new FileAlreadyExistsException("File: "+fileName+" already exists.");

        File newArduinoFile = new File((getPathToNewFile(fileName).toString()));
        arduinoMapper.objectToFile(arduino, newArduinoFile);
    }

    @Override
    public ArduinoModel read(String fileName) throws IOException {
        Path pathToFile = getPathToFile(fileName);
        return arduinoMapper.fileToObject(new File(String.valueOf(pathToFile)), ArduinoModel.class);
    }

    @Override
    public void update(ArduinoModel newArduino, String oldName) throws IOException {
        delete(oldName);
        create(newArduino, newArduino.getName());
    }

    @Override
    public void delete(String fileName) throws IOException {
        Path pathToFile = getPathToFile(fileName);
        Files.delete(pathToFile);
    }

    private Path getPathToFile (String fileName) throws IOException {
        URL path = resources.getResource(fileName);
        if (path == null)
            throw new FileNotFoundException("File: "+fileName+" is not in the resource bank.");
        return Path.of(path.getPath());
    }

    private Path getPathToNewFile(String fileName) throws UnexpectedException {
        return Path.of(getPathToResourceFolder().toString(), fileName);
    }

    private Path getPathToResourceFolder() throws UnexpectedException {
        URL path = resources.getResource(ORIENTATION_FILE_NAME);
        if (path == null)
            throw new UnexpectedException("FATAL ERROR. Could not localize resource folder.");
        return Path.of(path.getPath().replace(ORIENTATION_FILE_NAME, ""));
    }

    @Override
    public String[] listResources() throws IOException {

        Path directoryPath = getPathToResourceFolder();
        File resourceLibrary = (new File(directoryPath.toString()));
        String[] list = resourceLibrary.list((f, name) -> !name.startsWith("."));
        return list == null ? new String[]{"No resources in library"} : list;
    }
}
