package ua.pp.myshko.coefcalculator;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.FileWriter;

import static org.junit.Assert.*;

/**
 * @author M. Chernenko
 */
public class AppWebServiceTest {

    @Before
    public void setUp() throws Exception {

        FileWriter file1Writer = new FileWriter(FileHandlerImpl.FILE1_NAME);
        file1Writer.write("1.1;\n");
        file1Writer.write("2.2;\n");
        file1Writer.write("3.3;\n");
        file1Writer.write("4.4;\n");
        file1Writer.write("5.5;\n");
        file1Writer.write("6.6;\n");
        file1Writer.write("7.7;\n");
        file1Writer.write("8.8;\n");
        file1Writer.write("9.9;\n");
        file1Writer.close();

        FileWriter file2Writer = new FileWriter(FileHandlerImpl.FILE2_NAME);
        file2Writer.write("3.3;\n");
        file2Writer.write("4.4;\n");
        file2Writer.close();
    }

    @Test
    public void shouldExecuteParallel() throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    AppWebService appWebService = new AppWebService();
                    Response response = appWebService.get(0);
                    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                            "<result>1.1</result>", response.getEntity());
                }
            }.start();
        }
    }

    @Test
    public void shouldReturnCorrectResult_whenParallelUpdateDone() throws Exception {

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            new Thread() {
                @Override
                public void run() {
                    Params params = new Params();
                    params.setV2(1);
                    params.setV3(finalI);
                    params.setV4(finalI);

                    AppWebService appWebService = new AppWebService();
                    Response response = appWebService.post(params);

                    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                                    "<result>0</result>", response.getEntity());
                }
            }.start();
        }

        Thread.sleep(5000);

        FileHandlerImpl fileHandler = new FileHandlerImpl();
        assertEquals(12.1, fileHandler.getF2(0), 0);
        assertEquals(13.2, fileHandler.getF2(1), 0);
        assertEquals(14.3, fileHandler.getF2(2), 0);
        assertEquals(15.4, fileHandler.getF2(3), 0);
        assertEquals(16.5, fileHandler.getF2(4), 0);
        assertEquals(17.6, fileHandler.getF2(5), 0);
        assertEquals(18.7, fileHandler.getF2(6), 0);
        assertEquals(19.8, fileHandler.getF2(7), 0);
        assertEquals(10.9, fileHandler.getF2(8), 0);
        assertEquals(11.0, fileHandler.getF2(9), 0);
        assertEquals(0.0,  fileHandler.getF2(10), 0);
    }
}