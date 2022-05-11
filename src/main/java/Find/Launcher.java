package Find;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;
import java.util.List;


public class Launcher {
    @Option(name = "-r", metaVar = "subdirectory", usage = "Search in subdirectories")
    private boolean subdirectory;

    @Option(name = "-d", metaVar = "directory", usage = "Directory to search in")
    private String directory;

    @Argument(required = true, metaVar = "fileName", usage = "Input file name")
    private List<String> fileNames;

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar find.jar -d directory -r subdirectory FileName Input file name");
            parser.printUsage(System.err);
            return;
        }

        Find finder = new Find();
        try {
            finder.search(directory, fileNames, subdirectory);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
       new Launcher().launch(args);
    }
}



