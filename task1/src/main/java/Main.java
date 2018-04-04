import fs.CommonFile;
import fs.ListFile;
import fs.ListFileImpl;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static String rootPath = System.getProperty("user.dir");


    public static void main(String[] args) {

        if(new File(rootPath).exists()) {
            methodFS(rootPath); // comment/uncommnet to use differ method if need
            //mehtodStream(rootPath);
        } else {
            System.err.println("No dir " + rootPath);
        }
    }

    //Method1 using FileSystem and Queue
    public static void methodFS(String rootPath) {

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

    //Method2 using Stream
    public  static void mehtodStream(String rootPath){

        //get sorted by name list of path-files string
        List<String> listTextFiles = null;
        try {
            listTextFiles = Files.walk(new File(rootPath).toPath())
                    .filter(p -> p.toFile().isFile())
                    .sorted(Comparator.comparing(f -> f.getFileName().toString()))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(String.join("\n", listTextFiles));

        //concat all files to single file
        File outFile = new File(CommonFile.PATH_OUT);
        if (!outFile.exists()){
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Can't create file by " + CommonFile.PATH_OUT);
            }
        } else {
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Can't create file by " + CommonFile.PATH_OUT);
            }
        }
        Stream<String> files = listTextFiles.stream();
        try {
                files.map( (f) -> Paths.get(f))
                    .flatMap((path) -> {
                        try {
                            return Files.lines(path,  StandardCharsets.UTF_8);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .forEach(line -> {
                        try {
                            Files.write( Paths.get(CommonFile.PATH_OUT),
                                    (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                                    StandardOpenOption.APPEND );
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        } catch (UncheckedIOException e) {
                e.printStackTrace();
                CommonFile.createFile(listTextFiles); // if different encoding file, throw Exception
        }
    }
}
