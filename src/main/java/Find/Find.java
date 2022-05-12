package Find;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Find {

    public void search(String directory, List<String> fileNames, boolean subdirectory) throws IOException {
        File baseDir = new File(directory);

        if (!baseDir.exists()) throw new FileNotFoundException("There is no such directory");
        if (!baseDir.isDirectory()) throw new IllegalArgumentException("There is no such directory");

        Queue<File> dirTree = new ArrayDeque<>();
        addFilesToQueue(dirTree, baseDir);
        while (!dirTree.isEmpty()) {
            File currentFile = dirTree.remove();
            returnName(currentFile, fileNames);
            if (currentFile.isDirectory() && subdirectory) {
                addFilesToQueue(dirTree, currentFile);
            }
        }
    }

    private void returnName(File file, List<String> list) {
        String finalName = file.getName();
        if (list.contains(finalName)) {
            System.out.print(finalName + "\n");
        }
    }

    private void addFilesToQueue(Queue<File> queue, File file) {
        File[] files = file.listFiles();
        Objects.requireNonNull(files);
        queue.addAll(List.of(files));
    }
}