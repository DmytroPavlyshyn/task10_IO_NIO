package com.pavlyshyn.task2;

import java.io.*;

public class ReaderBenchmark {
    void testInputOutput(Reader inputStream, Writer outputStream) throws IOException {
        long start = System.nanoTime();
        int b;
        while ((b = inputStream.read()) != -1) {
            outputStream.write((byte) b);
        }
        long end = System.nanoTime() - start;
        System.out.println("Duration: " + end);
    }

    public static void main(String[] args) throws IOException {
        ReaderBenchmark r = new ReaderBenchmark();
        int sizeOfBuffer = 128;

        System.out.println("Test with buffer: ");
        for (int i = 1; i < 11; i++) {
            r.testInputOutput(
                    new BufferedReader(new FileReader("task2_2.txt"), sizeOfBuffer * i),
                    new BufferedWriter(new FileWriter("task2_3.txt"), sizeOfBuffer * i));
        }
        System.out.println("with file 200 mb ");
        r.testInputOutput(
                new BufferedReader(new FileReader("task2_1.txt"), sizeOfBuffer),
                new BufferedWriter(new FileWriter("task2_3.txt"), sizeOfBuffer));
        System.out.println("Test without buffer:");
        r.testInputOutput(new FileReader("task2_2.txt"), new FileWriter("task2_3.txt"));

    }
}
