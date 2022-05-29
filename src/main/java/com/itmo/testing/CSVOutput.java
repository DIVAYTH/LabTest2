package com.itmo.testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVOutput {
    public static void writeResult(Function function, double fromX, double toX, double step) {
        File file = new File("src/main/resources/" + function + ".csv");
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("X, " + function.toString() + "\n");
            double x = fromX;
            while (x <= toX) {
                try {
                    writer.write(x + ", " + function.calculate(x) + "\n");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                x += step;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}