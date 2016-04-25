package ua.pp.myshko.coefcalculator;

import javax.xml.ws.Response;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author M. Chernenko
 */
public interface RequestProcessor {

    double get(int v1) throws IOException;
    boolean post(int v2, int v3, int v4);
}
