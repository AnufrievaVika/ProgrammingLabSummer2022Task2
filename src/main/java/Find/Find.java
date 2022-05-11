package Find;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Find {

    public void search(String directory, List<String> fileNames, boolean subdirectory) throws IOException {
        File baseDir = new File(directory);

        if (!baseDir.exists()) throw new IOException("There is no such directory");
        if (!baseDir.isDirectory()) throw new NullPointerException();

        Queue<File> dirTree = new ConcurrentLinkedQueue<>();
        NonNullFun(dirTree, baseDir);
        while (!dirTree.isEmpty()) {
            File currentFile = dirTree.remove();
            if (subdirectory) {
                if (currentFile.isDirectory()) {
                    NonNullFun(dirTree, currentFile);
                } else {
                    returnName(currentFile, fileNames);
                }
            } else {
                if (!currentFile.isDirectory()) {
                    returnName(currentFile, fileNames);
                }
            }
        }
        System.err.println("Поиск окончен");
    }

    private void returnName(File a, List<String> b) {
        String finalName = a.getName();
        if (b.contains(finalName)) {
            System.out.print(finalName + "\n");
        }
    }

    private void NonNullFun (Queue <File> d, File e){
        File[] files = e.listFiles();
        Objects.requireNonNull(files);
        d.addAll(List.of(files));
        }
    }

