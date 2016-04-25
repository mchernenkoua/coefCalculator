package ua.pp.myshko.coefcalculator;

import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author M. Chernenko
 */
public class RequestProcessorImpl implements RequestProcessor {

    private static RequestProcessorImpl requestProcessorImpl;
    private FileHandler fileHandler = new FileHandlerImpl();

    private RequestProcessorImpl() {
    }

    public void init(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public static synchronized RequestProcessorImpl getInstance() {
        if (requestProcessorImpl == null) {
            requestProcessorImpl = new RequestProcessorImpl();
        }
        return requestProcessorImpl;
    }

    public synchronized double get(int v1) throws IOException {

        double v1Value = fileHandler.getF2(v1);
        if (v1Value > 10) {
            v1Value = 10;
            fileHandler.putF2(v1, v1Value);
        }
        return v1Value;
    }

    public synchronized boolean post(int v2, int v3, int v4) {

        try {
            double v3Value = fileHandler.getF1(v3) + v2;
            if (v3Value < 10) {
                v3Value += 10;
            }
            fileHandler.putF2(v4, v3Value);
        } catch (IOException e) {
            return true;
        }
        return false;
    }
}
