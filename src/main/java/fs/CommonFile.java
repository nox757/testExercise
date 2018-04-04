package fs;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CommonFile {

    private static final String PATH_OUT = System.getProperty("user.dir") + File.separator +"CommonFile.txt";

    public static void createFile(List<String> files) {
        try(FileOutputStream fos = new FileOutputStream(PATH_OUT)) {
            for (String path : files) {
                File file = new File(path);
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                    byte[] buffer = new byte[1024 * 1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("finished");
    }
}
