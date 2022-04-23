package Find;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FindFun {

    public FindFun() {
    }

    public Set<String> search(String directory, List<String> fileNames, boolean subdirectory) throws IOException {
        Set<String> result = new HashSet<>();
        Path path = Path.of(directory);
        File baseDir = new File(directory);

        if (!Files.exists(path)) throw new IOException("There is no such directory");

        if (!subdirectory) {
            for (String name : fileNames) {
                Path currentPath = path.resolve(name);
                if (Files.exists(currentPath)) {
                    result.add(name);
                }
            }
        } else {
            Queue<File> dirTree = new PriorityQueue<>();
            dirTree.addAll(List.of(Objects.requireNonNull(baseDir.listFiles())));
            while (!dirTree.isEmpty()) {
                File currentFile = dirTree.remove();
                if (currentFile.isDirectory()) {
                    dirTree.addAll(List.of(Objects.requireNonNull(currentFile.listFiles())));
                } else {
                    if (fileNames.contains(currentFile.getName())) {
                        result.add(currentFile.getName());
                    }
                }
            }
        }
        if (result.isEmpty()) System.out.println("Files not found");
        else System.out.println(result);
        return result;
    }

}

