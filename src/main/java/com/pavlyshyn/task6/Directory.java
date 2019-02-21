package com.pavlyshyn.task6;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Directory {
    void dirRecursive(Path path) throws IOException {
        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("Directory:" + dir + "attributes" + attrs.creationTime() );
                showAttributes(attrs);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("File:" + file + "attributes" + attrs);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("file failed:" + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
    private void showAttributes(BasicFileAttributes attributes){
        System.out.println("Creation time " + attributes.creationTime());
        System.out.println("Last modified time " + attributes.lastModifiedTime());
        System.out.println("Last access time " + attributes.lastAccessTime());
    }
    void dir(Path path) throws IOException {
        Files.list(path).forEach(System.out::println);
    }
    public static void main(String[] args) throws IOException {
        Directory directory = new Directory();
        directory.dir(Paths.get("E:/Epam JAVA/task10_IO_NIO/src/main/java/com/pavlyshyn/task1"));
    }
}
