package com.pavlyshyn.task5;

import java.io.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class SomeBuffer {
    public static final int BUFFER_SIZE = 128;
    public static void read(String path) throws IOException {
        FileChannel fileChannel = new FileInputStream(path).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        int numberElements = fileChannel.read(byteBuffer);
        while (numberElements!=-1){
            byteBuffer.flip();
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            while (intBuffer.hasRemaining()){
                System.out.println(intBuffer.get());
            }
            byteBuffer.clear();
            numberElements =fileChannel.read(byteBuffer);
        }
    }

    public static void write(String path, int[] data) throws IOException {
        FileChannel fileChannel = new FileOutputStream(path).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length*4);
        Arrays.stream(data).forEach(byteBuffer::putInt);
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException {
        SomeBuffer.write("task5.txt",new int[]{1,2,3,4,5,6,7,8});
        SomeBuffer.read("task5.txt");
    }
}