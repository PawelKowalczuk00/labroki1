package pl.edu.pwr.student.arduino.app;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 12.10.2020 na zajęciach

    Plik: JSONmapper.java

 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

final class JSONmapper<T> extends ObjectMapper {

    String objectToJson(T obj) throws JsonProcessingException {
        return writeValueAsString(obj);
    }

    void objectToFile(T obj, File file) throws IOException {
        writeValue(file, obj);
    }

    T jsonToObject(String json) throws JsonProcessingException {
        return readValue(json, new TypeReference<T>() {});
    }

    T fileToObject (File file, Class<T> type) throws IOException {
        return readValue(file, getTypeFactory().constructType(type) );
    }
}
