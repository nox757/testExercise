import fs.CommonFile;
import fs.ListFile;
import fs.ListFileImpl;


import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {


    public static String rootPath = "/home/nox757/Документы/new"; //System.getProperty("user.dir");

    public static void main(String[] args) {

        //getting list of path text files
        ListFile files = new ListFileImpl(rootPath);
        files.buildListFile();
        List<String> listTextFiles =  files.getListFile();

        //sorting path by name
        Collections.sort(listTextFiles, Comparator.comparing(f -> new File(f).getName()));
        System.out.println(String.join("\n", listTextFiles));

        //create summarize file
        CommonFile.createFile(listTextFiles);
    }
}
