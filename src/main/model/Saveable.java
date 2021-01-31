package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Saveable {

    //MODIFIES: testSave
    //EFFECTS: saves strings to file
    void save() throws FileNotFoundException, UnsupportedEncodingException;
}
