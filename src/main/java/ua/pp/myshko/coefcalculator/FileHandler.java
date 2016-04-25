package ua.pp.myshko.coefcalculator;

import java.io.IOException;

/**
 * @author M. Chernenko
 */
public interface FileHandler {

    double getF1(int index) throws IOException;
    double getF2(int index) throws IOException;
    void putF2(int index, double value) throws IOException;
}
