package ua.pp.myshko.coefcalculator;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

/**
 * @author M. Chernenko
 */
public class RequestProcessorImplTest {

    @Test
    public void shouldReturnTheSameInstance() throws Exception {

        RequestProcessor instance = RequestProcessorImpl.getInstance();
        RequestProcessor instance2 = RequestProcessorImpl.getInstance();

        assertNotNull(instance2);
        assertEquals(instance, instance2);

    }

    @Test
    public void shouldAnswerOnGetRequest_whenValueLessThan10() throws Exception {

        RequestProcessorImpl instance = RequestProcessorImpl.getInstance();

        FileHandler fileHandler = mock(FileHandler.class);
        when(fileHandler.getF2(anyInt())).thenReturn(1.2);

        instance.init(fileHandler);

        double result = instance.get(1);

        assertEquals(1.2, result, 0);
        verify(fileHandler, never()).putF2(anyInt(), anyDouble());
    }

    @Test
    public void shouldAnswerAndUpdateOnGetRequest_whenValueMoreThan10() throws Exception {

        RequestProcessorImpl instance = RequestProcessorImpl.getInstance();

        FileHandler fileHandler = mock(FileHandler.class);
        when(fileHandler.getF2(anyInt())).thenReturn(10.2);

        instance.init(fileHandler);

        double result = instance.get(1);

        assertEquals(10, result, 0);
        verify(fileHandler, times(1)).putF2(1, 10);
    }

    @Test
    public void shouldAnswerOnPostRequest_whenSumLessThan10() throws Exception {

        RequestProcessorImpl instance = RequestProcessorImpl.getInstance();

        FileHandler fileHandler = mock(FileHandler.class);
        when(fileHandler.getF1(anyInt())).thenReturn(1.2);

        instance.init(fileHandler);

        boolean result = instance.post(1, 2, 3);

        assertFalse(result);
        verify(fileHandler, times(1)).putF2(3, 12.2);
    }

    @Test
    public void shouldAnswerOnPostRequest_whenSumMoreThan10() throws Exception {

        RequestProcessorImpl instance = RequestProcessorImpl.getInstance();

        FileHandler fileHandler = mock(FileHandler.class);
        when(fileHandler.getF1(anyInt())).thenReturn(9.2);

        instance.init(fileHandler);

        boolean result = instance.post(1, 2, 3);

        assertFalse(result);
        verify(fileHandler, times(1)).putF2(3, 10.2);
    }
}