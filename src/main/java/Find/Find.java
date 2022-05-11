package Find;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Find {

    public Set<String> search(String directory, List<String> fileNames, boolean subdirectory) throws IOException {
        Set<String> result = new HashSet<>();
        Path path = Path.of(directory);
        File baseDir = new File(directory);

        if (!Files.exists(path)) throw new IOException("There is no such directory");

        Queue<File> dirTree = new PriorityQueue<>();
        NonNullFun(dirTree, baseDir);
        while (!dirTree.isEmpty()) {
            File currentFile = dirTree.remove();
            if (subdirectory && baseDir.isDirectory()) {
                if (currentFile.isDirectory()) {
                    NonNullFun(dirTree, currentFile);
                } else {
                    returnName(currentFile, fileNames, result);
                }
            } else if (!subdirectory && baseDir.isDirectory()) {
                if (!currentFile.isDirectory()) {
                    returnName(currentFile, fileNames, result);
                }
            }
        }
        if (result.isEmpty()) System.err.println("Files not found");
        else System.out.println(result);
        return result;
    }

    private void returnName(File a, List<String> b, Set<String> c) {
        String finalName = a.getName();
        if (b.contains(finalName)) {
            System.out.println(finalName);
            c.add(finalName);
        }
    }

        private void NonNullFun (Queue <File> d, File e){
            File[] files = e.listFiles();
            Objects.requireNonNull(files);
            d.addAll(List.of(files));
        }

    }

