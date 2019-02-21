package com.pavlyshyn.task7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Client {
    static InetSocketAddress localhost = new InetSocketAddress("localhost", 1111);
    static SocketChannel socketChannel;

    static {
        try {
            socketChannel = SocketChannel.open(localhost);
            log("Connecting to Server on port 1111...");
            socketChannel.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void messageReceiver() {
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
            try {
                socketChannel.read(byteBuffer);
                byteBuffer.flip();
                if (byteBuffer.limit() != 0) {
                    System.out.println(new String(byteBuffer.array()));
                }
                byteBuffer.clear();
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }

    public static void messageSender() {
        try {
            while (true) {
                byte[] message = new Scanner(System.in).nextLine().getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(message);
                socketChannel.write(buffer);

                log("sending...");
                buffer.clear();

                Thread.sleep(1000);

            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(Client::messageSender);
        executor.execute(Client::messageReceiver);
    }

    private static void log(String str) {
        System.out.println(str);
    }

}