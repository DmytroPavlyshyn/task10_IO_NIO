package com.pavlyshyn.task7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client {
    private InetSocketAddress localhost;
    private SocketChannel socketChannel;
    private boolean isOnline;

    void connect() throws IOException {
        localhost = new InetSocketAddress("localhost", 1111);
        socketChannel = SocketChannel.open(localhost);
        log("Connecting to server on port 1111...");
        socketChannel.configureBlocking(false);
        isOnline = true;
    }

    private void messageReceiver() {
        while (isOnline) {
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

    private void messageSender() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("For exit: close");
        try {
            while (isOnline) {
                String message = scanner.nextLine();
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                socketChannel.write(buffer);
                log("sending...");
                buffer.clear();
                if (message.matches("\\s*close\\s*")) {
                    disconnect();
                    return;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

    void disconnect() {
        try {
            log("disconnecting...");
            socketChannel.finishConnect();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isOnline = false;
        }
    }

    public void messageController() {
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(this::messageSender);
        executor.execute(this::messageReceiver);
        while (isOnline){               //TODO
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log("shotdown");
        ((ExecutorService) executor).shutdown();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect();
        client.messageController();


    }

    private static void log(String str) {
        System.out.println(str);
    }

}