package ua.pp.myshko.coefcalculator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author M. Chernenko
 */
public class FileHandlerImpl implements FileHandler {

    public static final String FILE1_NAME = "f1.csv";
    public static final String FILE2_NAME = "f2.csv";
    public static final String FILE_TMP_NAME = "tmpFile";

    private double getFromFile(String fileName, int line) throws IOException {

        Path filePath = new File(fileName).toPath();
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        FileReader fileReader = new FileReader(fileName);

        try (BufferedReader reader = new BufferedReader(fileReader)) {

            return reader.lines()
                    .skip(line)
                    .map(stringValue -> Double.parseDouble(stringValue.replace(";", "")))
                    .findFirst().orElse(0.0);
        }
    }

    private void putToFile(String fileName, int line, double value) throws IOException {

        Path filePath = new File(fileName).toPath();
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        FileReader fileReader = new FileReader(fileName);

        Path tmpFilePath = new File(FILE_TMP_NAME).toPath();
        if (!Files.exists(tmpFilePath)) {
            Files.createFile(tmpFilePath);
        }
        FileWriter fileWriter = new FileWriter(FILE_TMP_NAME);

        try (BufferedWriter writer = new BufferedWriter(fileWriter)) {

            try (BufferedReader reader = new BufferedReader(fileReader)) {
                int i = 0;
                String fileLine;
                while (i < line) {
                    fileLine = reader.readLine();
                    writer.write(fileLine == null ? "0.0" : fileLine);
                    writer.newLine();
                    i++;
                }
                writer.write("" + value + ";");
                writer.newLine();

                reader.readLine(); // skip one

                while ((fileLine = reader.readLine()) != null) {
                    writer.write(fileLine);
                    writer.newLine();
                }
            }

            writer.flush();
            writer.close();
            Files.copy(tmpFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Override
    public double getF1(int index) throws IOException {
        return getFromFile(FILE1_NAME, index);
    }

    @Override
    public double getF2(int index) throws IOException {
        return getFromFile(FILE2_NAME, index);
    }

    @Override
    public void putF2(int index, double value) throws IOException {
        putToFile(FILE2_NAME, index, value);
    }
}
