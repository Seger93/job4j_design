package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        Path dir = Path.of(argsName.get("d"));
        String exclude = argsName.get("e");
        List<Path> list = Search.search(dir, path -> path.toFile().getName().endsWith(exclude));
        File out = new File(argsName.get("o"));
        if (!dir.toFile().exists()) {
            throw new IllegalArgumentException("Not exist");
        }
        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Not directory");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Please input valid format of searching files");
        }
        zip.packFiles(list, out);
    }
}