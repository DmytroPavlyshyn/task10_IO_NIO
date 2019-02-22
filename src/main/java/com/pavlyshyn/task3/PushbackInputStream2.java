package com.pavlyshyn.task3;


import java.io.*;


public class PushbackInputStream2 extends FilterInputStream {
    protected byte[] buf;

    protected int pos;

    private void ensureOpen() throws IOException {
        if (in == null)
            throw new IOException("Stream closed");
    }

    public PushbackInputStream2(InputStream in, int size) {
        super(in);
        if (size <= 0) {
            throw new IllegalArgumentException("size <= 0");
        }
        this.buf = new byte[size];
        this.pos = size;
    }

    public PushbackInputStream2(InputStream in) {
        this(in, 1);
    }

    public int read() throws IOException {
        ensureOpen();
        if (pos < buf.length) {
            return buf[pos++] & 0xff;
        }
        return super.read();
    }

    public void unread(int b) throws IOException {
        ensureOpen();
        if (pos == 0) {
            throw new IOException("Push back buffer is full");
        }
        buf[--pos] = (byte) b;
    }

    public int available() throws IOException {
        throw new UnsupportedOperationException();
    }

    public long skip(long n) throws IOException {
        throw new UnsupportedOperationException();
    }

    public boolean markSupported() {
        throw new UnsupportedOperationException();
    }

    public synchronized void mark(int readlimit) {
        throw new UnsupportedOperationException();
    }

    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    public synchronized void close() throws IOException {
        if (in == null)
            return;
        in.close();
        in = null;
        buf = null;
    }

    @Override
    public String toString() {
        return "PushbackInputStream2{" +
                "in=" + in +
                "} " + super.toString();
    }

    public static void main(String[] args) throws IOException {
        PushbackInputStream2 p = new PushbackInputStream2(
                new DataInputStream(
                        new ByteArrayInputStream("Hello world".getBytes())));
        int p1;
        while ((p1 = p.read()) != -1) {
            System.out.println((byte) p1);
        }


        PushbackInputStream2 c = new PushbackInputStream2(
                new DataInputStream(
                        new ByteArrayInputStream("Hello world".getBytes())));
        System.out.println(c.read());
        c.unread(300);
        while ((p1 = c.read()) != -1) {
            System.out.println((byte) p1);
        }
    }
}
