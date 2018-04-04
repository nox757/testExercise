package fs;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommonFile {

    public static String PATH_OUT = System.getProperty("user.dir") + File.separator +
                                    "CommonFile" + System.currentTimeMillis() +".txt";

    public static void createFile(List<String> files) {
        try(FileOutputStream fos = new FileOutputStream(PATH_OUT)) {
            for (String path : files) {
                System.out.println(path);
                File file = new File(path);
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                    byte[] buffer = new byte[1024 * 1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
