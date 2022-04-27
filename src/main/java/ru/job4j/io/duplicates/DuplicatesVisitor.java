package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> visitedFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty visitedFile = new FileProperty(Files.size(file), file.getFileName().toString());
        Path path = file.toAbsolutePath();
        if (visitedFiles.containsKey(visitedFile)) {
            visitedFiles.get(visitedFile).add(path);
        } else {
            visitedFiles.put(visitedFile, new ArrayList<>(List.of(path)));
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        return visitedFiles.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
    }
}