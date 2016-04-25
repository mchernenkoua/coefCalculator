package ua.pp.myshko.coefcalculator;

import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author M. Chernenko
 */
public class FileHandlerImplTest {

    @Before
    public void setUp() throws Exception {

        FileWriter file1Writer = new FileWriter(FileHandlerImpl.FILE1_NAME);
        file1Writer.write("1.2;\n");
        file1Writer.write("8.2;\n");
        file1Writer.close();

        FileWriter file2Writer = new FileWriter(FileHandlerImpl.FILE2_NAME);
        file2Writer.write("2.2;\n");
        file2Writer.write("9.2;\n");
        file2Writer.close();
    }

    @Test
    public void testGetF1() throws Exception {

        FileHandlerImpl fileHandler = new FileHandlerImpl();
        double f1 = fileHandler.getF1(0);

        assertEquals(1.2, f1, 0);
    }

    @Test
    public void testGetF2() throws Exception {

        FileHandlerImpl fileHandler = new FileHandlerImpl();
        double f2 = fileHandler.getF2(0);

        assertEquals(2.2, f2, 0);
    }

    @Test
    public void testPutF2() throws Exception {

        FileHandlerImpl fileHandler = new FileHandlerImpl();
        double f2 = fileHandler.getF2(0);
        assertEquals(2.2, f2, 0);

        fileHandler.putF2(0, 3.2);

        f2 = fileHandler.getF2(0);
        assertEquals(3.2, f2, 0);

        f2 = fileHandler.getF2(1);
        assertEquals(9.2, f2, 0);

        f2 = fileHandler.getF2(2);
        assertEquals(0.0, f2, 0);

    }
}