package de.gfn.coffeesystemsmart.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {


    // Attribute
    private static Logger instance;
    private final String filename;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy || HH:mm:ss");

    //Konstruktor
    Logger(String filename){this.filename = filename;}

    // Zugriff auf die eigene Instanz
    private static Logger getInstance(String filename) {
        if(instance == null){
            instance = new Logger(filename);
        }
        return instance;
    }

    // Methode zum Log's speichern in einer Datei
    public void log(String className, String message, String row) {
        String timestamp = LocalDateTime.now().format(formatter);
        String line = timestamp + " | " + className + " | " + row + " | " + message;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) { //<-- True = Datei anhängen // nicht überschreiben
            writer.write(line);
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Error by writing in Log ");

        }
    }

    // Methode zum Löschen von Logs
    public void clear(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename,false))) { //<-- false = überschreiben
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
