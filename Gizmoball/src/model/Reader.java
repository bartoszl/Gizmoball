package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private BufferedReader reader;
    private String line = "";
    private String regex = "[\\s\\t]";
    private List<String[]> data;

    public Reader () {
        data = new ArrayList<String[]>();
    }

    public List<String[]> readFromFile(File fileName) throws IOException {
        List<String[]> tempHolder = new ArrayList<String[]>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while((line = reader.readLine()) != null) {
                String[] components = line.split(regex);
                tempHolder.add(components);
            }
            return tempHolder;
        } catch (IOException e) {
            throw new IOException();
        }
    }
}